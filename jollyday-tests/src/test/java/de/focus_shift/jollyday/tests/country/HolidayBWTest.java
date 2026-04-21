package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BOTSWANA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayBWTest {
  @Test
  void ensuresHolidays() {
    assertFor(BOTSWANA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("SIR_SERETSE_KHAMA_DAY", JULY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 30).and()
      .hasFixedHoliday("BOTSWANA_DAY", OCTOBER, 1).and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
      .check();
  }
}
