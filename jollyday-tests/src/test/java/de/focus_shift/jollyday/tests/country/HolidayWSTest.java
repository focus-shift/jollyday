package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAMOA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;

class HolidayWSTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAMOA)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("DAY_AFTER_NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 1).validFrom(Year.of(1962)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Christian (Easter-based) holidays
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
