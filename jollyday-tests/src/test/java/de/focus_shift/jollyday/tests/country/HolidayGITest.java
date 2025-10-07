package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GIBRALTAR;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayGITest {

  @Test
  void ensuresHolidays() {

    assertFor(GIBRALTAR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1973))
        .validBetween(Year.of(1974), Year.of(2500))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("COMMONWEALTH_DAY", FEBRUARY, 15)
      .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", APRIL, 28)
        .notValidBetween(Year.of(1900), Year.of(2020))
        .validBetween(Year.of(2021), Year.of(2500))
      .and()
      .hasFixedHoliday("MAY_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("NATIONAL_DAY_OF_GIBRALTAR", SEPTEMBER, 10)
        .notValidBetween(Year.of(1900), Year.of(1966))
        .validBetween(Year.of(1967), Year.of(2500))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
