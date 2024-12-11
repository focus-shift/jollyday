package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayDETest {

  @Test
  void ensuresHolidays() {
    assertFor(GERMANY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("UNIFICATION", JUNE, 17)
        .notBetween(Year.of(1900), Year.of(1953))
        .between(Year.of(1954), Year.of(1990))
        .notBetween(Year.of(1991), Year.of(2500))
      .and()
      .hasFixedHoliday("UNIFICATION_GERMANY", OCTOBER, 3)
        .notBetween(Year.of(1900), Year.of(1989))
        .between(Year.of(1990), Year.of(2500))
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .between(Year.of(2017), Year.of(2017))
      .and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
