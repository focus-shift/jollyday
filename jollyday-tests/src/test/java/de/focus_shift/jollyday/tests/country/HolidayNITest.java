package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.NICARAGUA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.MAY;
import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.DECEMBER;

class HolidayNITest {

  @Test
  void ensuresHolidays() {
    assertFor(NICARAGUA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("AIR_FORCE_DAY", FEBRUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ARMY_DAY", MAY, 27).and()
      .hasFixedHoliday("LIBERATION", JULY, 19).and()
      .hasFixedHoliday("BATTLE_JACINTO", SEPTEMBER, 14).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 15).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("MAUNDY_THURSDAY")
      .check();
  }
}

