---
name: add-holiday-calendar
description: Guide for adding new country holiday calendars to Jollyday, including XML configuration, holiday definitions, description properties, and test coverage
---

# How to Add a New Holiday Calendar for a Country

This guide explains how to add a new country's holiday calendar to Jollyday based on existing implementations and pull requests.

## Overview

Jollyday stores holiday calendars as XML files in `jollyday-core/src/main/resources/holidays/`. Each country has its own file named `Holidays_[country_code].xml`.

## Step 1: Create the Holiday Calendar File

Create a new file `jollyday-core/src/main/resources/holidays/Holidays_[country_code].xml` with the following structure:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="[country_code]" description="[Country Name]"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <Holidays>
    <!-- Add your holidays here -->
  </Holidays>

  <Sources>
    <Source>[URL to official source or Wikipedia]</Source>
    <Source of="ISO 3166">[URL to official iso 3166 source]</Source>
    <Source of="ISO 3166-2">[URL to official iso 3166-2 source for subdivisions]</Source>
  </Sources>
</Configuration>
```

### Root Element Attributes

| Attribute     | Description                                              |
|---------------|----------------------------------------------------------|
| `hierarchy`   | ISO 3166-1 alpha-2 country code (e.g., "de", "us", "fr") |
| `description` | Full country name in English                             |

## Step 2: Add Holiday Definitions

### Fixed Date Holidays

For holidays on a fixed calendar date:

```xml
<Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
```

With validity period:

```xml
<Fixed month="OCTOBER" day="31" validFrom="2017" descriptionPropertiesKey="REFORMATION_DAY"/>
<Fixed month="JUNE" day="19" validFrom="2021" descriptionPropertiesKey="JUNETEENTH"/>
<Fixed month="OCTOBER" day="31" validFrom="2017" validTo="2017" descriptionPropertiesKey="REFORMATION_DAY"/>
```

### Fixed Weekday Holidays

For holidays on a specific weekday in a month:

```xml
<FixedWeekday which="FIRST" weekday="MONDAY" month="SEPTEMBER" validFrom="1895" descriptionPropertiesKey="LABOUR_DAY"/>
<FixedWeekday which="THIRD" weekday="MONDAY" month="JANUARY" validFrom="1986" descriptionPropertiesKey="MARTIN_LUTHER_KING"/>
<FixedWeekday which="FOURTH" weekday="THURSDAY" month="NOVEMBER" validFrom="1863" descriptionPropertiesKey="THANKSGIVING"/>
<FixedWeekday which="LAST" weekday="MONDAY" month="MAY" validFrom="1968" descriptionPropertiesKey="MEMORIAL_DAY"/>
```

**`which` values**: `FIRST`, `SECOND`, `THIRD`, `FOURTH`, `LAST`

### Christian Movable Holidays

For Christian holidays that depend on Easter:

```xml
<ChristianHoliday type="GOOD_FRIDAY"/>
<ChristianHoliday type="EASTER_MONDAY"/>
<ChristianHoliday type="ASCENSION_DAY"/>
<ChristianHoliday type="WHIT_MONDAY"/>
<ChristianHoliday type="CORPUS_CHRISTI"/>
<ChristianHoliday type="PENTECOST"/>
```

With optional `localizedType` for observance holidays:

```xml
<ChristianHoliday type="GOOD_FRIDAY" localizedType="OBSERVANCE"/>
```

### Islamic Holidays

For Islamic calendar-based holidays:

```xml
<IslamicHoliday type="RAMADAN_END"/>
<IslamicHoliday type="ID_AL_FITR"/>
<IslamicHoliday type="ID_AL_FITR_2"/>
<IslamicHoliday type="ID_AL_FITR_3"/>
<IslamicHoliday type="ARAFAAT"/>
<IslamicHoliday type="ID_UL_ADHA"/>
<IslamicHoliday type="ID_UL_ADHA_2"/>
<IslamicHoliday type="ID_UL_ADHA_3"/>
<IslamicHoliday type="NEWYEAR"/>
<IslamicHoliday type="MAWLID_AN_NABI"/>
```

### Holidays Relative to Fixed Dates

For holidays calculated relative to a fixed date:

```xml
<RelativeToFixed validFrom="1990" descriptionPropertiesKey="REPENTANCE_PRAYER">
  <Weekday>WEDNESDAY</Weekday>
  <When>BEFORE</When>
  <Date month="NOVEMBER" day="23"/>
</RelativeToFixed>
```

**`When` values**: `BEFORE`, `AFTER`

### Holidays Relative to Other Weekday Holidays

For holidays calculated relative to another moving holiday:

```xml
<RelativeToWeekdayInMonth weekday="FRIDAY" when="BEFORE" descriptionPropertiesKey="SERVICE_REDUCTION">
  <FixedWeekday which="LAST" weekday="MONDAY" month="MAY"/>
</RelativeToWeekdayInMonth>
```

### Moving Conditions

For holidays that shift to weekdays when falling on weekends:

```xml
<Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR">
  <MovingCondition substitute="SATURDAY" with="PREVIOUS" weekday="FRIDAY"/>
  <MovingCondition substitute="SUNDAY" with="NEXT" weekday="MONDAY"/>
</Fixed>
```

## Step 3: Add Description Properties

Add entries to `jollyday-core/src/main/resources/descriptions/holiday_descriptions.properties`:

```properties
holiday.description.NEW_YEAR                    = New Year's Day
holiday.description.LABOUR_DAY                  = Labour Day
holiday.description.CHRISTMAS                   = Christmas
holiday.description.Independence_DAY            = Independence Day
holiday.description.NATIONAL_DAY                = National Day
```

### Key Naming Conventions

- Use UPPERCASE with underscores: `NEW_YEAR`, `LABOUR_DAY`, `GOOD_FRIDAY`
- For multi-word holidays: `FIRST_CHRISTMAS_DAY`, `SECOND_CHRISTMAS_DAY`
- Country-specific: `REFORMATION_DAY`, `UNIFICATION_GERMANY`, `MARTIN_LUTHER_KING`

### Localized Descriptions

Add entries to locale-specific files:

- `holiday_descriptions_de.properties` (German)
- `holiday_descriptions_fr.properties` (French)
- `holiday_descriptions_es.properties` (Spanish)
- `holiday_descriptions_pt.properties` (Portuguese)
- `holiday_descriptions_nl.properties` (Dutch)
- `holiday_descriptions_sv.properties` (Swedish)
- `holiday_descriptions_el.properties` (Greek)

## Step 4: Add Country Description

Add the country name to `jollyday-core/src/main/resources/descriptions/country_descriptions.properties`:

```properties
country.description.xx = Your Country Name
```

And localized versions if applicable.

## Step 5: Add Regional/Subdivision Holidays

For countries with regional holidays (states, provinces):

```xml
<SubConfigurations hierarchy="[region_code]" description="[Region Name]">
  <Holidays>
    <Fixed month="MARCH" day="8" descriptionPropertiesKey="INTERNATIONAL_WOMAN" validFrom="2019"/>
  </Holidays>
</SubConfigurations>
```

Example with nested subdivisions:

```xml
<SubConfigurations hierarchy="by" description="Bavaria">
  <Holidays>
    <Fixed month="JANUARY" day="6" descriptionPropertiesKey="EPIPHANY"/>
  </Holidays>

  <SubConfigurations hierarchy="mu" description="Munich">
    <Holidays>
      <Fixed day="15" month="AUGUST" descriptionPropertiesKey="ASSUMPTION_DAY"/>
    </Holidays>
  </SubConfigurations>
</SubConfigurations>
```

## Step 6: Add Test Coverage

Create a test in `jollyday-tests/src/test/java/de/focus_shift/jollyday/tests/country/` using the `CalendarCheckerApi`:

```java
package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.YOUR_COUNTRY; // The HolidayCalendar constant for your country
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.DECEMBER;

class HolidaysXXTest { // Replace XX with your country code

  @Test
  void ensuresHolidays() {
    assertFor(YOUR_COUNTRY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26)
        .validFrom(Year.of(1990))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validFrom(Year.of(2000))
      .check();
  }
}
```

### CalendarCheckerApi Methods

| Method                             | Description                                 |
|------------------------------------|---------------------------------------------|
| `hasFixedHoliday(key, month, day)` | Checks for a fixed date holiday             |
| `hasChristianHoliday(key)`         | Checks for a Christian/Easter-based holiday |
| `hasIslamicHoliday(key)`           | Checks for an Islamic calendar holiday      |
| `hasEthiopianOrthodoxHoliday(key)` | Checks for Ethiopian Orthodox holiday       |
| `validFrom(Year)`                  | Specifies the starting year                 |
| `validTo(Year)`                    | Specifies the ending year                   |
| `validBetween(Year, Year)`         | Specifies a valid year range                |
| `notValidBetween(Year, Year)`      | Specifies an invalid year range             |
| `inSubdivision(...codes)`          | For regional/state-specific holidays        |
| `and()`                            | Chains another holiday assertion            |
| `check()`                          | Performs the assertions                     |

### Example with Subdivisions

```java
@Test
void ensuresHolidays() {
  assertFor(YOUR_COUNTRY)
    .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
    .hasFixedHoliday("REGIONAL_HOLIDAY", MARCH, 8)
      .inSubdivision("state-code")
      .validFrom(Year.of(2020))
    .and()
    .check();
}
```

## Complete Example: Germany (Simplified)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration hierarchy="de" description="Germany"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
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
    <Source of="ISO 3166">https://www.iso.org/obp/ui/#iso:code:3166:DE</Source>
    <Source of="ISO 3166-2">https://en.wikipedia.org/wiki/ISO_3166-2:DE</Source>
  </Sources>
</Configuration>
```

## Common Attributes Reference

| Attribute                  | Description                | Example                  |
|----------------------------|----------------------------|--------------------------|
| `month`                    | Month (English, uppercase) | `JANUARY`, `DECEMBER`    |
| `day`                      | Day of month               | `1`, `25`                |
| `weekday`                  | Day of week                | `MONDAY`, `FRIDAY`       |
| `which`                    | Which occurrence           | `FIRST`, `LAST`, `THIRD` |
| `validFrom`                | Start year                 | `1990`, `2021`           |
| `validTo`                  | End year                   | `1990`, `2020`           |
| `descriptionPropertiesKey` | Key for description        | `NEW_YEAR`               |
| `localizedType`            | Holiday type variant       | `OBSERVANCE`             |

## Available Month Values

`JANUARY`, `FEBRUARY`, `MARCH`, `APRIL`, `MAY`, `JUNE`, `JULY`, `AUGUST`, `SEPTEMBER`, `OCTOBER`, `NOVEMBER`, `DECEMBER`

## Available Weekday Values

`MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`

## Available ChristianHoliday Types

- `GOOD_FRIDAY`
- `EASTER`
- `EASTER_MONDAY`
- `EASTER_SATURDAY`
- `EASTER_TUESDAY`
- `ASCENSION_DAY`
- `WHIT_MONDAY`
- `PENTECOST`
- `PENTECOST_MONDAY`
- `CORPUS_CHRISTI`
- `CARNIVAL`
- `MARDI_GRAS`
- `MAUNDY_THURSDAY`
- `ASH_WEDNESDAY`

## Available IslamicHoliday Types

- `NEWYEAR`
- `MAWLID_AN_NABI`
- `LAILAT_AL_BARAT`
- `LAILAT_AL_MIRAJ`
- `LAILAT_AL_QADR`
- `ASCHURA`
- `RAMADAN`
- `RAMADAN_END`
- `ID_AL_FITR`, `ID_AL_FITR_2`, `ID_AL_FITR_3`
- `ARAFAAT`
- `ID_UL_ADHA`, `ID_UL_ADHA_2`, `ID_UL_ADHA_3`

## Step 7: Register the Calendar in HolidayCalendar.java

Add the new calendar to `jollyday-core/src/main/java/de/focus_shift/jollyday/core/HolidayCalendar.java`.

### Organization Pattern

The `HolidayCalendar` enum follows a strict organization pattern:
- Calendars are listed in **alphabetical order** by their ISO country code
- Each line starts with calendars beginning with a specific letter (A, B, C, etc.)
- Multiple calendars sharing the same starting letter are grouped on the same line

### Example Structure

```java
public enum HolidayCalendar {
  ANTARCTICA("AQ"), ALBANIA("AL"), ANDORRA("AD"), ARGENTINA("AR"), ARMENIA("AM"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BELARUS("BY"), BELGIUM("BE"), BERMUDA("BM"), ...
  CANADA(Locale.CANADA.getCountry()), CAYMAN_ISLANDS("KY"), ...
}
```

### Adding Your Calendar

1. Find the line that starts with the appropriate letter for your country code
2. Add your calendar in alphabetical order on that line
3. Use the format: `CALENDAR_NAME("ISO_CODE")`

**Example for adding Antarctica (AQ):**
- Find line starting with "A" calendars
- Insert `ANTARCTICA("AQ"),` in alphabetical position

**Example for a fictional "Zebra" (ZB):**
- Add to the "Z" line: `ZEBRA("ZB"), ZIMBABWE("ZW");`

## References

- Schema: `https://focus_shift.de/jollyday/schema/holiday/holiday.xsd`
- XSD Location in repo: `jollyday-core/src/main/resources/focus_shift.de/jollyday/schema/holiday/holiday.xsd`
- Examples: Review existing `Holidays_*.xml` files in `jollyday-core/src/main/resources/holidays/`

## Running Tests

### Prerequisites

**Java Version**: This project requires **Java 17**. The project uses [asdf](https://asdf-vm.com/) for version management.

Before running tests, ensure you have Java 17 available:

```bash
# Verify Java version
java -version
# Should output: openjdk version "17.x.x"
```

**Note**: If you don't have asdf installed:
1. Install asdf: https://asdf-vm.com/guide/getting-started.html
2. Install Java: `asdf install`

### Build the Project

Before running tests, ensure the verify phase has been executed:

```bash
./mvnw verify -DskipTests
```

### Run Country-Specific Test

Run the test for your specific country:

```bash
./mvnw test -Dtest=HolidayXXTest -pl jollyday-tests
```

Replace `XX` with your country code in **uppercase** (e.g., `HolidayDETest` for Germany, `HolidayGITest` for Gibraltar).

**Test naming convention**: `Holiday` + uppercase country code + `Test`

**Examples**:
- Germany: `./mvnw test -Dtest=HolidayDETest -pl jollyday-tests`
- Gibraltar: `./mvnw test -Dtest=HolidayGITest -pl jollyday-tests`
- United States: `./mvnw test -Dtest=HolidayUSTest -pl jollyday-tests`

### Full Test Suite

To run all tests:

```bash
./mvnw test
```

### Test Output Format

A successful test run looks like:

```
[INFO] Running de.focus_shift.jollyday.tests.country.HolidayDETest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
