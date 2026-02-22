package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ECUADOR;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayECTest {

  @Test
  void ensuresHolidays() {
    assertFor(ECUADOR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("PICHINCHA", MAY, 24).and()
      .hasFixedHoliday("BOLIVAR", JULY, 24).and()
      .hasFixedHoliday("INDEP_QUITO", AUGUST, 10).and()
      .hasFixedHoliday("INDEP_GUYAQUIL", OCTOBER, 9).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("INDEP_CUENCA", NOVEMBER, 3).and()
      .hasFixedHoliday("FOUND_QUITO", DECEMBER, 6).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
