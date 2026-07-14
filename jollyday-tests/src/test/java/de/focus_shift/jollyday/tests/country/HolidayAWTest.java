package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARUBA;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;

class HolidayAWTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(ARUBA)
      // AV 2013 Art. 22: only King's Day and Labour Day have a codified substitution rule;
      // every other fixed holiday below is observed on its literal date, weekend or not.
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BETICO_CROES_DAY", JANUARY, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARUBA_NATIONAL_ANTHEM_AND_FLAG_DAY", MARCH, 18).validBetween(YEAR_FROM, YEAR_TO).and()
      // Dutch royal tradition: moves BACKWARD to the preceding Saturday, not forward
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("CARNIVAL_MONDAY", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
