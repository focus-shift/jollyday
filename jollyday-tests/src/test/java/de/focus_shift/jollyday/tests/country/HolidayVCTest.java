package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_VINCENT_AND_THE_GRENADINES;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayVCTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_VINCENT_AND_THE_GRENADINES)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", MARCH, 14).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 27).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasFixedWeekdayHoliday("CARNIVAL_MONDAY", FIRST, MONDAY, JULY).and()
      .hasFixedWeekdayHoliday("CARNIVAL_TUESDAY", FIRST, TUESDAY, JULY)
      .check();
  }
}
