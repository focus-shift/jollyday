package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.HONDURAS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayHNTest {

  @Test
  void ensuresHolidays() {

    assertFor(HONDURAS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("AMERICAS_DAY", APRIL, 14).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 15).and()
      .hasFixedHoliday("FRANCISCO_MORAZAN_DAY", OCTOBER, 3).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12).and()
      .hasFixedHoliday("ARMY_DAY", OCTOBER, 21).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER")
      .check();
  }
}
