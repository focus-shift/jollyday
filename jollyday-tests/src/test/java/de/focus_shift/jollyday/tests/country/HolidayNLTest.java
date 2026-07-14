package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NETHERLANDS;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.FIVE_YEARS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

class HolidayNLTest {

  @Test
  void ensuresHolidays() {
    assertFor(NETHERLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1966))
        .validFrom(Year.of(1967))
      .and()
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .notValidBetween(Year.of(1900), Year.of(2013))
        .validFrom(Year.of(2014))
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", AUGUST, 31)
        .validBetween(Year.of(1885), Year.of(1947))
        .notValidBetween(Year.of(1948), Year.of(2500))
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", APRIL, 30)
        .validBetween(Year.of(1948), Year.of(1979))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", APRIL, 30)
        .validBetween(Year.of(1980), Year.of(2013))
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 5)
        .validBetween(Year.of(1945), Year.of(1989))
        .every(FIVE_YEARS, Year.of(1945))
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 5)
        .validFrom(Year.of(1990))
      .and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("PENTECOST_MONDAY")
      .check();
  }
}
