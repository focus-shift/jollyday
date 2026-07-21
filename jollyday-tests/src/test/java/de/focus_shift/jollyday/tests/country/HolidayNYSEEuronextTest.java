package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.NYSE_EURONEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

class HolidayNYSEEuronextTest {

  @Test
  void ensuresHolidays() {

    assertFor(NYSE_EURONEXT)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
