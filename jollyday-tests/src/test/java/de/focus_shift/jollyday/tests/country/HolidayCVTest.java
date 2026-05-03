package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.CAPE_VERDE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayCVTest {

  @Test
  void ensuresHolidays() {
    assertFor(CAPE_VERDE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("DEMOCRACY_DAY", JANUARY, 13).and()
      .hasFixedHoliday("HEROES_DAY", JANUARY, 20).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("YOUTH_DAY", JUNE, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 5).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}
