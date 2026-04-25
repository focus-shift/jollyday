---
name: write-holiday-tests
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

### Fixed Weekday Holidays

```java
// First Monday in September
.hasFixedWeekdayHoliday("LABOUR_DAY", java.time.DayOfWeek.MONDAY, SEPTEMBER, java.time.temporal.TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY))

// Third Monday in January (MLK Day)
.hasFixedWeekdayHoliday("MARTIN_LUTHER_KING", JANUARY, 3)
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
.hasIslamicHoliday("ID_AL_FITR", 2024, 10, 10)  // type, year, month, day
.hasIslamicHoliday("ID_UL_ADHA", 2024, 6, 16)
.hasIslamicHoliday("NEWYEAR", 2024, 7, 7)
```

### Regional/Subdivision Holidays

```java
// Holiday in specific region
.hasSubConfig("bw")
  .hasFixedHoliday("REPELLENCE_PRAYER", NOVEMBER, 20)
  .and()
.check();

// Multiple regions
.hasSubConfig("by")
  .hasFixedHoliday("EPIPHANY", JANUARY, 6)
  .and()
.hasSubConfig("bw")
  .hasFixedHoliday("EPIPHANY", JANUARY, 6)
  .and()
.check();
```

### Not a Holiday

```java
.notHasHoliday(DECEMBER, 31)
.notHasSubConfig("bw")
  .hasFixedHoliday("SOME_HOLIDAY", DECEMBER, 24)
  .and()
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
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
      .hasChristianHoliday("GOOD_FRIDAY")
      .hasChristianHoliday("EASTER_MONDAY")
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
      .hasChristianHoliday("ASCENSION_DAY")
      .hasChristianHoliday("WHIT_MONDAY")
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26)
      .and()
      // Regional holidays - Bavaria
      .hasSubConfig("by")
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
      .hasFixedHoliday("ASCENSION_DAY", MAY)
      .and()
      // Regional holidays - Baden-Württemberg
      .hasSubConfig("bw")
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
      .hasFixedHoliday("REPELLENCE_PRAYER", NOVEMBER)
      .and()
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
    .hasFixedHoliday("NEW_YEAR", JANUARY, 1, 2023)
    .check();
}
```

### Historical Changes

```java
@Test
void ensuresHistoricalChanges() {
  assertFor(HolidayCalendar.GERMANY)
    // Reformation Day only since 2017
    .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31, 2017)
    .notHasHoliday(OCTOBER, 31, 2016)
    .check();
}
```

## Best Practices

1. **Comprehensive coverage**: Test all holidays including regional ones
2. **Valid ranges**: Test holidays with `validFrom`/`validTo` constraints
3. **Edge cases**: Test years where holidays fall on weekends
4. **Clear naming**: Use descriptive test method names
5. **Group related tests**: Use `.and()` to chain related assertions

