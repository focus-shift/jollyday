package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.LESOTHO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JULY;
import static java.time.Month.OCTOBER;

class HolidayLSTest {
  @Test
  void ensuresHolidays() {
    assertFor(LESOTHO)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("MOSHOESHOE_DAY", MARCH, 11).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("AFRICA_DAY", MAY, 25).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JULY, 17).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 4).and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
      .check();
  }
}
