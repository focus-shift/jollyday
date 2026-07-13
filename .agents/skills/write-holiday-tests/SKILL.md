---
name: Write Holiday Tests
description: Write country-specific holiday tests using CalendarCheckerApi
---

# How to Write Holiday Tests

This guide explains how to write tests for new or modified holiday calendars using the `CalendarCheckerApi`.

## File Location

Create test file at: `jollyday-tests/src/test/java/de/focus_shift/jollyday/tests/[CountryName]Test.java`

**Example naming:**
- Germany: `HolidayDETest.java`
- United States: `HolidayUSTest.java`
- France: `HolidayFRTest.java`

## CalendarCheckerApi

The `CalendarCheckerApi` provides fluent assertions for testing holidays:

```java
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static java.time.Month.JANUARY;
import static java.time.Month.FEBRUARY;
```

### Basic Usage

```java
@Test
void ensuresHolidays() {
  assertFor(GERMANY)
    .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
    .hasChristianHoliday("GOOD_FRIDAY").and()
    .hasIslamicHoliday("ID_UL_ADHA")
    .check();
}
```

## Assertion Methods

### Fixed Date Holidays

```java
// Basic fixed holiday
.hasFixedHoliday("NEW_YEAR", JANUARY, 1)

// With validFrom year
.hasFixedHoliday("JUNETEENTH", JUNE, 19, 2021)

// With validFrom and validTo
.hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31, 2017, null)
```

### Christian (Easter-based) Holidays

```java
.hasChristianHoliday("GOOD_FRIDAY")
.hasChristianHoliday("EASTER_MONDAY")
.hasChristianHoliday("ASCENSION_DAY")
.hasChristianHoliday("WHIT_MONDAY")
.hasChristianHoliday("CORPUS_CHRISTI")
```

### Islamic Calendar Holidays

```java
.hasIslamicHoliday("ID_AL_FITR")  // type, year, month, day
.hasIslamicHoliday("ID_UL_ADHA")
.hasIslamicHoliday("NEWYEAR")
```

### Fixed Weekday Holidays

The XSD defines three distinct "weekday-based" holiday types. Each has its own assertion method,
which independently recomputes the expected date rather than calling into the production
calculator — this keeps the check a genuine verification of the XML config, not a tautology
against the same code being tested.

```java
// FixedWeekday: Nth/last weekday of a month, e.g. US Thanksgiving
.hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER)
  .validFrom(Year.of(1863))

// FixedWeekdayBetweenFixed: first matching weekday between two fixed dates, e.g. Iceland's Husband's Day
.hasFixedWeekdayBetweenFixedHoliday("HUSBANDS_DAY", FRIDAY, MonthDay.of(JANUARY, 19), MonthDay.of(JANUARY, 25))

// FixedWeekdayRelativeToFixed: Nth weekday before/after/closest-to a fixed anchor date, e.g. Iceland's First Day of Summer
.hasFixedWeekdayRelativeToFixedHoliday("FIRST_DAY_SUMMER", FIRST, THURSDAY, AFTER, MonthDay.of(APRIL, 18))
```

`Occurrence` (`FIRST`/`SECOND`/`THIRD`/`FOURTH`/`LAST`) and `Relation` (`BEFORE`/`AFTER`/`CLOSEST`) come from
`de.focus_shift.jollyday.core.spi`. Note: `Occurrence.LAST` is **not supported** for
`hasFixedWeekdayRelativeToFixedHoliday` (throws `UnsupportedOperationException`) — production's
day-offset calculation has no defined behavior for `LAST` in that specific holiday type.

### Regional/Subdivision Holidays

```java
// Holiday in specific region
.hasFixedHoliday("REPELLENCE_PRAYER", NOVEMBER, 20)
  .inSubdivision("bw")
.check();

// Multiple regions
.hasFixedHoliday("EPIPHANY", JANUARY, 6)
  .inSubdivision("by")
.and()
.hasFixedHoliday("EPIPHANY", JANUARY, 6)
  .inSubdivision("bw")
.check();
```

## Complete Test Example

```java
package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;

class HolidayGETest {

  @Test
  void ensuresHolidays() {
    assertFor(HolidayCalendar.GERMANY)
      // National holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      // Regional holidays - Bavaria
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .inSubdivision("by")
      .and()
      .hasFixedHoliday("ASCENSION_DAY", MAY)
        .inSubdivision("by")
      .and()
      // Regional holidays - Baden-Württemberg
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .inSubdivision("bw")
      .and()
      .hasFixedHoliday("REPELLENCE_PRAYER", NOVEMBER)
        .inSubdivision("bw")
      .and()
      // Christian holidays
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
```

## Testing Edge Cases

### Moving Conditions (Weekend Shift)

```java
@Test
void ensuresMovingCondition() {
  assertFor(HolidayCalendar.US)
    // New Year falls on Saturday, shifts to Friday
    .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SATURDAY, FRIDAY)
    .check();
}
```

### Historical Changes

```java
@Test
void ensuresHistoricalChanges() {
  assertFor(HolidayCalendar.GERMANY)
    // Reformation Day only since 2017
    .hasFixedHoliday("REFORMATION", OCTOBER, 31)
      .validBetween(Year.of(2017), Year.of(2017))
    .check();
}
```

## Best Practices

1. **Comprehensive coverage**: Test all holidays including regional ones
2. **Valid ranges**: Test holidays with `validFrom`/`validTo` constraints
3. **Edge cases**: Test years where holidays fall on weekends
4. **Clear naming**: Use descriptive test method names
5. **Group related tests**: Use `.and()` to chain related assertions but not at the end of the chain before `.check()`
6. **Year ranges for Islamic holiday calendars**: When a calendar includes at least one **Islamic holiday**, in test files, define `YEAR_FROM = 1900` and `YEAR_TO = 2173` constants, and use `.validBetween(Year.of(YEAR_FROM), Year.of(YEAR_TO))` for all holiday assertions since the entire calendar's valid range is constrained by these attributes.

