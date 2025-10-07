package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.CZECH_REPUBLIC;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayCZTest {

  @Test
  void ensuresHolidays() {
    assertFor(CZECH_REPUBLIC)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("LIBERATION", MAY, 8).and()
      .hasFixedHoliday("CYRUS_METHODIUS", JULY, 5).and()
      .hasFixedHoliday("HUS", JULY, 6).and()
      .hasFixedHoliday("WENCELAS", SEPTEMBER, 28).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 28).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", NOVEMBER, 17).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .validFrom(Year.of(2016))
        .notValidBetween(Year.of(1900), Year.of(2015))
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
