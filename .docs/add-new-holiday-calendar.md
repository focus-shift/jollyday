# Adding a New Holiday Calendar to Jollyday

This guide explains step-by-step how to add a new holiday calendar to Jollyday, based on the Armenia example from PR #704.

## Overview

To add a new holiday calendar, you need to:

1. [Create a holiday definition XML file](#step-1-create-the-calendar-holiday-configuration-xml-file)
2. [Add the new holiday calendar enum](#step-2-add-new-holiday-calendar)
3. [Add translations](#step-3-add-translations-for-the-new-holiday-calendar)
4. [Write tests](#step-4-write-tests)
5. [Create a pull request](#step-5-create-pull-request)

---

## Step 1: Create the Calendar Holiday Configuration XML File

Navigate to:

```
jollyday-core/src/main/resources/holidays
```

Create a new XML file named after the country code ([ISO 3166-1 alpha-2]).

Example for Armenia:

```
Holidays_am.xml
```

### Structure Example

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration hierarchy="am" description="Armenia"
               xmlns="https://focus_shift.de/jollyday/schema/holiday"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="https://focus_shift.de/jollyday/schema/holiday https://focus_shift.de/jollyday/schema/holiday/holiday.xsd">
  <Holidays>
    <Fixed month="JANUARY" day="1" descriptionPropertiesKey="NEW_YEAR"/>
  </Holidays>
</Configuration>
```

### Requirements

Ensure your XML file:

* Is well-formed
* Matches the schema definition
* Uses valid elements (e.g., `Fixed`, `RelativeToFixed`, `ChristianHoliday`)

### Notes

* Use ISO 3166-1 alpha-2 country codes (e.g., `am` for Armenia)
* Follow existing country files as templates
* Keep the file minimal and consistent with existing implementations

---

## Step 2: Add New Holiday Calendar

Add a new entry to the `HolidayCalendar` enum:

```
jollyday-core/src/main/java/de/focus_shift/jollyday/core/HolidayCalendar.java
```

Example:

```java
ARMENIA("am")
```

### Notes

* The code must match the XML file name (`am` ↔ `Holidays_am.xml`)
* Keep alphabetical order if applicable

---

## Step 3: Add Translations for the New Holiday Calendar

Add translations for the country and its subdivisions.

File location:

```
jollyday-core/src/main/resources/descriptions/country_descriptions.properties
```

Example:

```properties
country.description.am        = Armenia
country.description.am.ag     = Aragatsotn
country.description.am.ar     = Ararat
country.description.am.av     = Armavir
country.description.am.er     = Yerevan
country.description.am.gr     = Gegharkunik
country.description.am.kt     = Kotayk
country.description.am.lo     = Lori
country.description.am.sh     = Shirak
country.description.am.su     = Syunik
country.description.am.tv     = Tavush
country.description.am.vd     = Vayots Dzor
```

### Notes

* Use consistent naming with ISO subdivision codes
* Only add subdivisions if they are supported in the XML

---

## Step 4: Write Tests

Create a test class:

```
jollyday-tests/src/test/java/de/focus_shift/jollyday/tests/country/HolidayAMTest.java
```

### Approach (Based on PR #704)

Tests should use the `CalendarCheckerApi` and follow the established fluent style.

### Example Test

```java
package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARMENIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;

class HolidayAMTest {

  @Test
  void ensuresHolidays() {

    assertFor(ARMENIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 5).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 6).and()
      .hasFixedHoliday("ARMY_DAY", JANUARY, 28).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("ARMENIAN_GENOCIDE_REMEMBRANCE_DAY", APRIL, 24).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_AND_PEACE_DAY", MAY, 9).and()
      .hasFixedHoliday("REPUBLIC_DAY", MAY, 28).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 5).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 21).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER")
      .check();
  }
}
```

### Requirements

Ensure:

* Tests reflect the XML configuration
* Only representative holidays are tested
* Fluent API (`assertFor(...)`) is used
* Tests remain readable and maintainable

Run tests using:

```
./mvnw verify
```

---

## Step 5: Create Pull Request

When submitting your PR:

* Include:

  * XML file
  * Enum update
  * Translations
  * Tests
* Reference official sources for holidays (government websites preferred)
* Keep the PR focused on a single country
* Provide a clear description of the changes

---

## Checklist

* [ ] XML file created
* [ ] HolidayCalendar enum updated
* [ ] Translations added
* [ ] Tests implemented
* [ ] Tests passing (`./mvnw verify`)
* [ ] PR created with description

---

## Reference

This guide is based on the Armenia implementation from PR #704.

---

[ISO 3166-1 alpha-2]: https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
