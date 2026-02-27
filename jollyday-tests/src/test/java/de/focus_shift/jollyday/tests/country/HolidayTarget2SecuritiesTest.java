package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TARGET2_SECURITIES;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

class HolidayTarget2SecuritiesTest {

  @Test
  void ensuresHolidays() {

    assertFor(TARGET2_SECURITIES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(Year.of(2015))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(2015))
      .and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26)
        .validFrom(Year.of(2015))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .validFrom(Year.of(2015))
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validFrom(Year.of(2015))
      .check();
  }
}
