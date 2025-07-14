package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static de.focus_shift.jollyday.core.HolidayCalendar.CANADA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.DECEMBER;

class HolidayCATest {

  @Test
  void ensuresHolidays() {
    assertFor(CANADA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CANADA_DAY", JULY, 1)
        .canBeShiftedFrom(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}

