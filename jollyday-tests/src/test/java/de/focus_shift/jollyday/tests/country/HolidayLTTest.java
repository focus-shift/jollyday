package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.LITHUANIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayLTTest {

  @Test
  void ensuresHolidays() {
    assertFor(LITHUANIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("UNIFICATION", FEBRUARY, 16).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MARCH, 11).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("JOHANNIS_DAY", JUNE, 24).and()
      .hasFixedHoliday("STATEHOOD", JULY, 6).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2)
        .between(Year.of(2020), Year.of(2500)).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 25).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}

