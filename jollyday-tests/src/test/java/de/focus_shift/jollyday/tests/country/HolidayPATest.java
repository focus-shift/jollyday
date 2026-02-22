package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.PANAMA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayPATest {

  @Test
  void ensuresHolidays() {
    assertFor(PANAMA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("MARTYRS_DAY", JANUARY, 9).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("SEPARATION", NOVEMBER, 3).and()
      .hasFixedHoliday("FLAG_DAY", NOVEMBER, 4).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 28).and()
      .hasFixedHoliday("MOTHERS_DAY", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CLEAN_MONDAY").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}

