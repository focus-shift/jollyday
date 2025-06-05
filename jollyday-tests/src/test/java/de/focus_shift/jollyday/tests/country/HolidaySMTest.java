package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAN_MARINO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidaySMTest {

  @Test
  void ensuresSanMarinoHolidays() {
    assertFor(SAN_MARINO)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("FEAST_OF_SAINT_AGATHA", FEBRUARY, 5).and()
      .hasFixedHoliday("ANNIVERSARY_OF_THE_ARENGO", MARCH, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("LIBERATION_FROM_FASCISM", JULY, 28).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("THE_FEAST_OF_SAINT_MARINUS_AND_REPUBLIC_DAY", SEPTEMBER, 3).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
