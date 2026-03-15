package de.focus_shift.jollyday.tests.country;

import static de.focus_shift.jollyday.core.HolidayCalendar.MONTENEGRO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;

import org.junit.jupiter.api.Test;

class HolidayMETest {

  @Test
  void ensuresHolidays() {
    assertFor(MONTENEGRO)
        .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .and()
        .hasFixedHoliday("CHRISTMAS_EVE", JANUARY, 6)
        .and()
        .hasFixedHoliday("CHRISTMAS", JANUARY, 7)
        .and()
        .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .and()
        .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 21)
        .and()
        .hasFixedHoliday("STATEHOOD", JULY, 13)
        .and()
        .hasChristianHoliday("EASTER")
        .and()
        .hasChristianHoliday("GOOD_FRIDAY")
        .and()
        .hasChristianHoliday("EASTER_MONDAY")
        .check();
  }
}
