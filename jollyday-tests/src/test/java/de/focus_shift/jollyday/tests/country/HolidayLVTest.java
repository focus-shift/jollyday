package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.LATVIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayLVTest {

  @Test
  void ensuresHolidays() {
    assertFor(LATVIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 4).and()
      .hasFixedHoliday("MIDSUMMER_EVE", JUNE, 23).and()
      .hasFixedHoliday("MIDSUMMER", JUNE, 24).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 18).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}

