package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_PIERRE_AND_MIQUELON;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayPMTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_PIERRE_AND_MIQUELON)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 14).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
