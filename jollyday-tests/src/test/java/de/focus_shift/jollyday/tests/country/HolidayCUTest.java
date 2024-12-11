package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.CUBA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayCUTest {

  @Test
  void ensuresHolidays() {
    assertFor(CUBA)
      .hasFixedHoliday("TRIUMPH_OF_THE_REVOLUTION", JANUARY, 1).and()
      .hasFixedHoliday("VICTORY_OF_FIDEL_CASTRO", JANUARY, 2).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("DAY_BEFORE_THE_COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", JULY, 25).and()
      .hasFixedHoliday("COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", JULY, 26).and()
      .hasFixedHoliday("DAY_AFTER_THE_COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", JULY, 27).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 10).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
