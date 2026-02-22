package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BELARUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayBYTest {

  @Test
  void ensuresHolidays() {
    assertFor(BELARUS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MARCH, 15).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY", MAY, 9).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 3).and()
      .hasFixedHoliday("OCT_REVOLUTION", NOVEMBER, 7).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}

