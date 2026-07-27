# Support for non-working/rest days (#733)

## Problem

Weekends are non-working/rest days that are not public holidays. In most
western countries they fall on Saturday/Sunday, but in many Middle Eastern,
North African, and some Asian and Central American countries they fall on
different weekdays (e.g. Friday/Saturday, Friday-only, Sunday-only). Jollyday
currently has no way to express or query this — see
[focus-shift/jollyday#733](https://github.com/focus-shift/jollyday/issues/733).

## Scope

In scope: recurring weekly rest days (which weekdays are the "weekend" for a
given calendar), queryable per calendar/subdivision and per year, with
historical transitions supported (a country can change its weekend days over
time).

Out of scope: irregular, one-off non-working days (government-declared bridge
days, election days, etc.). These aren't rule-based and would need an
entirely different, annually-updated data model. A separate feature/issue if
ever pursued.

## Prior art: Nager.Date

[Nager.Date](https://github.com/nager/Nager.Date) models this via a
`WeekendProvider`/`IWeekendProvider` (a `Set<DayOfWeek>` plus first/last
weekend day) and a static `WeekendSystem` lookup table
(`Dictionary<CountryCode, IWeekendProvider>`) with a handful of named presets
(`Universal` = Sat/Sun, `SemiUniversal` = Fri/Sat, `FridayOnly`, `SundayOnly`,
`FridaySunday`). It's hardcoded in code, has no historical/temporal modeling
(their own source has a `// TODO handle launch dates in weekends` on the UAE
entry, which changed its weekend from Fri/Sat to Sat/Sun in 2022), and no
subdivision-level overrides. Useful as a concept validator and as a research
lead for which countries have non-standard weekends, but not adopted as a
data source as-is — jollyday's convention (see repo history) is to verify
each calendar fact against a primary/statutory source, not third-party
aggregators.

## Design

### Schema (`jollyday-core`)

Add a `Weekend` complex type to
`jollyday-core/src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd`:

```xsd
<xsd:complexType name="Weekend">
  <xsd:attribute name="validFrom" type="xsd:int"/>
  <xsd:attribute name="validTo" type="xsd:int"/>
  <xsd:attribute name="days" type="xsd:string" use="required"/> <!-- comma-separated Weekday values, e.g. "FRIDAY,SATURDAY" -->
</xsd:complexType>
```

`Configuration` gets a new child element:

```xsd
<xsd:element name="Weekend" type="Weekend" maxOccurs="unbounded" minOccurs="0"/>
```

`maxOccurs="unbounded"` so a calendar can express a historical transition as
multiple `Weekend` entries with non-overlapping `validFrom`/`validTo` ranges
— the same pattern already used for holiday validity windows. Element order
in the schema must be double-checked against JAXB/Jackson binding behavior:
per prior experience in this codebase, wrong element order in the XSD can
silently drop data at parse time rather than failing loudly.

### SPI (`jollyday-core`)

New interface `de.focus_shift.jollyday.core.spi.WeekendConfiguration`:

```java
public interface WeekendConfiguration {
  @NonNull Set<Weekday> days();
  Optional<Integer> validFrom();
  Optional<Integer> validTo();
}
```

`HolidayCalendarConfiguration` gets a new method:

```java
@NonNull Stream<WeekendConfiguration> weekends();
```

### Bindings (`jollyday-jaxb`, `jollyday-jackson`)

Both modules add a generated/hand-written `Weekend` model type and an
adapter implementing `WeekendConfiguration`, following the exact pattern
already used for `Fixed`, `ChristianHoliday`, etc. in each module (e.g.
`JacksonFixed` in jollyday-jackson).

### Resolution & inheritance

A new resolver (in `jollyday-core`, alongside existing holiday resolution
logic) picks the applicable `WeekendConfiguration` for a requested calendar
and year:

1. Look at the calendar's own `weekends()` stream; pick the entry whose
   `validFrom`/`validTo` covers the requested year.
2. If none present or none match, walk up via `CalendarHierarchy` to the
   parent calendar's configuration and repeat.
3. If no calendar in the chain defines a matching entry, fall back to the
   hardcoded default `{SATURDAY, SUNDAY}`.

This mirrors how subdivisions already inherit/override holiday
configuration.

### Public API (`HolidayManager`)

```java
/**
 * Returns the weekend days (non-working days of the week) for the
 * requested year and hierarchy structure.
 */
public abstract @NonNull Set<DayOfWeek> getWeekendDays(@NonNull Year year, @NonNull String... args);

/**
 * Returns true if the given date is a non-working day: either a weekend
 * day (per getWeekendDays) or a holiday (per isHoliday, any HolidayType).
 */
public boolean isNonWorkingDay(@NonNull LocalDate date, @NonNull String... args) {
  return getWeekendDays(Year.of(date.getYear()), args).contains(DayOfWeek.from(date))
      || isHoliday(date, args);
}
```

`isNonWorkingDay` must OR against the existing `isHoliday(date, args)` across
all `HolidayType`s, not just `PUBLIC_HOLIDAY`, so it never disagrees with
what `isHoliday()` already reports for the same date.

### Data rollout

This plan covers infrastructure only. Every calendar defaults to
Saturday/Sunday via the fallback in the resolver; **no XML calendar files are
changed**. Each calendar with a non-standard weekend (UAE, Saudi Arabia,
Israel, Iran, etc.) is added in its own follow-up issue/PR with a cited
statutory/government source, exactly like new country calendars are added
today. Nager.Date's exception table is used only to identify candidates for
those follow-ups, not copied as accepted data.

## Testing

- **jollyday-core:** resolver unit tests — no `Weekend` element → default
  Sat/Sun; single element → applies to all years; multiple elements with
  `validFrom`/`validTo` → correct entry picked per year including boundary
  years; subdivision override vs. inherited-from-parent.
- **jollyday-jaxb / jollyday-jackson:** XML fixture containing a `Weekend`
  element round-tripped through each binding; assert the resulting
  `weekends()` stream matches, in both modules independently (not just one)
  — this codebase has a known failure mode where XSD element order silently
  drops data in one binding while the other still works.
- **jollyday-tests:** integration coverage of `getWeekendDays` /
  `isNonWorkingDay` through the public `HolidayManager` API for at least one
  calendar using the default and (once a follow-up lands) one with an
  explicit override.

## Module impact

| Module | Change |
|---|---|
| `jollyday-core` | XSD `Weekend` type/element, `WeekendConfiguration` SPI, resolver, `HolidayManager.getWeekendDays`/`isNonWorkingDay` |
| `jollyday-jaxb` | `Weekend` binding + adapter to `WeekendConfiguration` |
| `jollyday-jackson` | `JacksonWeekend` model + adapter to `WeekendConfiguration` |
| `jollyday-tests` | Cross-module resolver + API tests |
| XML calendar data | Untouched in this plan — follow-up PRs per country |

No changes to the existing `holidays()`/`subConfigurations()` contract on
`HolidayCalendarConfiguration` beyond adding `weekends()`.
