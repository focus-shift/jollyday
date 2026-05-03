---
name: Add Subdivision Holiday Configurations
description: Add subdivision holiday configurations based on ISO 3166-2 codes
---

# How to Add Subdivisions

This guide explains how to add subdivision holiday configurations for subdivisions (states, provinces, regions) based on ISO 3166-2 codes.

## Overview

Subdivisions allow you to define holidays that are specific to certain subdivision within a country. For example, in Germany, Bavaria (BY) has different holidays than Berlin (BE).

## File Location

Edit the country's XML file: `jollyday-core/src/main/resources/holidays/Holidays_[country_code].xml`

## ISO 3166-2 Codes

Subdivision codes follow the ISO 3166-2 standard:

| Format     | Example    | Meaning                    |
|------------|------------|----------------------------|
| `XX-YY`    | `DE-BY`    | Germany - Bavaria          |
| `XX-YY-ZZ` | `DE-BY-MU` | Germany - Bavaria - Munich |

The subdivision `hierarchy` attribute uses **only the regional part** (without the country prefix).

## Basic Structure

```xml
<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <!-- Subdivision-specific holidays go here -->
  </Holidays>
</SubConfigurations>
```

### Example - Germany (Baden-Württemberg)

```xml
<SubConfigurations hierarchy="bw" description="Baden-Württemberg">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
    <Fixed month="NOVEMBER" day="1" descriptionPropertiesKey="ALL_SAINTS"/>
    <ChristianHoliday type="CORPUS_CHRISTI"/>
  </Holidays>
</SubConfigurations>
```

## Adding a New Subdivision

### Step 1: Find the ISO 3166-2 Code

Look up the subdivision code:

| Country       | Source                                      |
|---------------|---------------------------------------------|
| Germany       | https://en.wikipedia.org/wiki/ISO_3166-2:DE |
| United States | https://en.wikipedia.org/wiki/ISO_3166-2:US |
| France        | https://en.wikipedia.org/wiki/ISO_3166-2:FR |

**Example - Germany codes:**

| Code | Region                 |
|------|------------------------|
| `bw` | Baden-Württemberg      |
| `by` | Bavaria                |
| `be` | Berlin                 |
| `bb` | Brandenburg            |
| `he` | Hesse                  |
| `nw` | North Rhine-Westphalia |

### Step 2: Add SubConfigurations Element

Insert the subdivision in your country's XML file:

```xml
<!-- Inside the root Configuration element, after <Holidays> and before closing </Configuration> -->

<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
  </Holidays>
</SubConfigurations>
```

### Step 3: Add Sources (Optional)

Document the source of subdivision holiday information:

```xml
<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
  </Holidays>
  <Sources>
    <Source>https://www.region-official-website.gov/holidays</Source>
  </Sources>
</SubConfigurations>
```

## Nested Subdivisions

Subdivisions can be nested for more granular regions (cities within states):

```xml
<SubConfigurations hierarchy="by" description="Bavaria">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
  </Holidays>

  <!-- Munich - city within Bavaria -->
  <SubConfigurations hierarchy="mu" description="Munich">
    <Holidays>
      <Fixed month="AUGUST" day="15" descriptionPropertiesKey="ASSUMPTION_DAY"/>
    </Holidays>
  </SubConfigurations>

  <!-- Augsburg - city within Bavaria -->
  <SubConfigurations hierarchy="ag" description="Augsburg">
    <Holidays>
      <Fixed month="AUGUST" day="8" descriptionPropertiesKey="PEACE"/>
      <Fixed month="AUGUST" day="15" descriptionPropertiesKey="ASSUMPTION_DAY"/>
    </Holidays>
  </SubConfigurations>
</SubConfigurations>
```

## Complete Example - Germany

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="de" description="Germany"
       xmlns="https://focus_shift.de/jollyday/schema/holiday"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <!-- National holidays apply to all Germany -->
  <Holidays>
    <Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
    <Fixed month="MAY" day="1" descriptionPropertiesKey="LABOUR_DAY"/>
    <Fixed month="DECEMBER" day="25" descriptionPropertiesKey="FIRST_CHRISTMAS_DAY"/>
    <Fixed month="DECEMBER" day="26" descriptionPropertiesKey="SECOND_CHRISTMAS_DAY"/>
    <ChristianHoliday type="GOOD_FRIDAY"/>
    <ChristianHoliday type="EASTER_MONDAY"/>
    <ChristianHoliday type="ASCENSION_DAY"/>
    <ChristianHoliday type="WHIT_MONDAY"/>
  </Holidays>

  <Sources>
    <Source>https://en.wikipedia.org/wiki/Public_holidays_in_Germany</Source>
    <Source of="ISO 3166-2">https://en.wikipedia.org/wiki/ISO_3166-2:DE</Source>
  </Sources>
  
  <!-- Regional holidays for Bavaria only -->
  <SubConfigurations hierarchy="by" description="Bavaria">
    <Holidays>
      <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
      <Fixed month="NOVEMBER" day="1" descriptionPropertiesKey="ALL_SAINTS"/>
      <ChristianHoliday type="CORPUS_CHRISTI"/>
    </Holidays>
  </SubConfigurations>

  <!-- Regional holidays for Berlin only -->
  <SubConfigurations hierarchy="be" description="Berlin">
    <Holidays>
      <Fixed month="MARCH" day="8" descriptionPropertiesKey="INTERNATIONAL_WOMAN" validFrom="2019"/>
    </Holidays>
  </SubConfigurations>
</Configuration>
```

## Holiday Types in Subdivisions

All holiday types can be used in subdivisions:

```xml
<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <!-- Fixed date -->
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>

    <!-- Fixed weekday -->
    <FixedWeekday which="FIRST" weekday="MONDAY" month="MAY" descriptionPropertiesKey="LABOUR_DAY"/>

    <!-- Christian (Easter-based) -->
    <ChristianHoliday type="CORPUS_CHRISTI"/>

    <!-- Relative to fixed date -->
    <RelativeToFixed descriptionPropertiesKey="REPENTANCE_PRAYER">
      <Weekday>WEDNESDAY</Weekday>
      <When>BEFORE</When>
      <Date month="NOVEMBER" day="23"/>
    </RelativeToFixed>
  </Holidays>
</SubConfigurations>
```

## Validity Periods

Subdivision holidays can have validity periods:

```xml
<SubConfigurations hierarchy="[subdivision_code]" description="[Subdivision Name]">
  <Holidays>
    <!-- Only valid from 2019 onwards -->
    <Fixed month="MARCH" day="8" descriptionPropertiesKey="INTERNATIONAL_WOMAN" validFrom="2019"/>

    <!-- Only valid for specific year -->
    <Fixed month="MAY" day="8" descriptionPropertiesKey="LIBERATION" validFrom="2020" validTo="2020"/>

    <!-- Only valid in specific range -->
    <Fixed month="OCTOBER" day="31" descriptionPropertiesKey="REFORMATION_DAY" validFrom="2018"/>
  </Holidays>
</SubConfigurations>
```

## Testing Subdivisions

Tests for subdivisions use the `.inSubdivision()` method chained after the holiday assertion:

```java
import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;

@Test
void ensuresBavarianHolidays() {
  assertFor(GERMANY)
    .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("by").and()
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("by").and()
    .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("by")
    .check();
}

@Test
void ensuresBadenWurttembergHolidays() {
  assertFor(GERMANY)
    .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("bw")
    .check();
}

@Test
void ensuresNestedSubdivisions() {
  // Munich is nested within Bavaria (by)
  assertFor(GERMANY)
    .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "mu")
    .check();
}

@Test
void ensuresMultipleSubdivisions() {
  // A holiday can be valid in multiple subdivisions
  assertFor(GERMANY)
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("bw").and()
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("by").and()
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("nw")
    .check();
}
```

### inSubdivision() Method Details

- **Signature**: `Properties inSubdivision(final String... subdivisions)`
- **Usage**: Chain after any holiday assertion method
- **Nested subdivisions**: For cities within states, include both codes (e.g., `inSubdivision("by", "mu")` for Munich in Bavaria)
- **Valid in subdivision**: The method checks that a holiday is present in the specified subdivision

### Example from HolidayDETest.java

```java
@Test
void ensuresAllHolidaysFor2024() {
  assertFor(GERMANY)
    // National holidays (all of Germany)
    .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
    .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
    .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
    
    // Baden-Württemberg specific holidays
    .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("bw").and()
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("bw").and()
    .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("bw").and()
    
    // Bavaria specific holidays
    .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("by").and()
    .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("by").and()
    .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("by").and()
    
    // Munich (nested within Bavaria)
    .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "mu")
    
    .check();
}
```

## Best Practices

1. **Use ISO codes**: Always use official ISO 3166-2 subdivision codes
2. **Clear descriptions**: Include the full subdivision name in the `description` attribute
3. **Document sources**: Add `<Sources>` elements for subdivision holiday references
4. **Nested when appropriate**: Use nested SubConfigurations for e.g. cities within states
5. **Consistent ordering**: Place SubConfigurations after the main `<Holidays>` section
6. **Validity periods**: Use `validFrom`/`validTo` for historical accuracy

## Common Subdivision Examples

### United States (California)

```xml
<SubConfigurations hierarchy="CA" description="California">
  <Holidays>
    <Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
    <FixedWeekday which="THIRD" weekday="MONDAY" month="JANUARY" descriptionPropertiesKey="MLK_DAY"/>
  </Holidays>
</SubConfigurations>
```

### Germany (Bavaria)

```xml
<SubConfigurations hierarchy="by" description="Bavaria">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
    <Fixed month="NOVEMBER" day="1" descriptionPropertiesKey="ALL_SAINTS"/>
    <ChristianHoliday type="CORPUS_CHRISTI"/>
  </Holidays>
</SubConfigurations>
```

### France (Corsica)

```xml
<SubConfigurations hierarchy="2C" description="Corsica">
  <Holidays>
    <Fixed month="MAY" day="15" descriptionPropertiesKey="CORSICA_LIBERATION"/>
  </Holidays>
</SubConfigurations>
```

## ISO 3166-2 Reference

| Country       | ISO Code | Subdivision Codes                                              |
|---------------|----------|----------------------------------------------------------------|
| Germany       | DE       | be, bb, bw, by, hb, he, hh, mv, ni, nw, rp, sl, sn, st, sh, th |
| United States | US       | CA, NY, TX, FL, ... (state codes)                              |
| France        | FR       | 01, 02, ... (department codes)                                 |
| Austria       | AT       | WI, B, K, ... (state codes)                                    |

**Sources**: https://en.wikipedia.org/wiki/ISO_3166-2
