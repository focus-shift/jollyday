package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_LUCIA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayLCTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_LUCIA)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("DAY_AFTER_NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 22).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 13).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI").and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FIRST, MONDAY, OCTOBER)
      .check();
  }
}
