package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TARGET;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

class HolidayTargetTest {

  @Test
  void ensuresHolidays() {

    assertFor(TARGET)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(Year.of(2000))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validFrom(Year.of(2000))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(1999))
      .and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26)
        .validFrom(Year.of(2000))
      .and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31)
        .validBetween(Year.of(1999), Year.of(1999))
        .notValidBetween(Year.of(2000), Year.of(2000))
        .validBetween(Year.of(2001), Year.of(2001))
        .notValidBetween(Year.of(2002), Year.of(2500))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .validFrom(Year.of(2000))
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validFrom(Year.of(2000))
      .check();
  }
}
