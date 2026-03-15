package de.focus_shift.jollyday.tests.country;

import static de.focus_shift.jollyday.core.HolidayCalendar.DOW_JONES_STOXX;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

import org.junit.jupiter.api.Test;

class HolidayDJSTOXXTest {

  @Test
  void ensuresHolidays() {
    assertFor(DOW_JONES_STOXX)
        .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .and()
        .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .and()
        .hasChristianHoliday("GOOD_FRIDAY")
        .and()
        .hasChristianHoliday("EASTER_MONDAY")
        .check();
  }
}
