package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.MALTA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayMTTest {

  @Test
  void ensuresHolidays() {

    assertFor(MALTA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("SAINT_PAUL_SHIPWRECK", FEBRUARY, 10).and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", MARCH, 31).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("SETTE_GIUGNO", JUNE, 7).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("VICTORY", SEPTEMBER, 8).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 21).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("REPUBLIC_DAY", DECEMBER, 13).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
