package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BONAIRE_SINT_EUSTATIUS_AND_SABA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayBQTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(BONAIRE_SINT_EUSTATIUS_AND_SABA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      // Dutch royal tradition: moves backward to the preceding Saturday, not forward
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO).and()
      // Whit Monday is explicitly NOT a recognized public holiday in the Caribbean Netherlands
      .hasChristianHoliday("PENTECOST").validBetween(YEAR_FROM, YEAR_TO).and()

      /* Sint Eustatius */
      .hasFixedHoliday("EMANCIPATION_DAY", JULY, 1)
        .inSubdivision("se")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("STATIA_DAY", NOVEMBER, 16)
        .inSubdivision("se")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      /* Saba */
      // "de dag, vallende na de op Saba gehouden carnavalsoptocht" (Regeling feestdagen
      // ambtenaren BES, Art. 3) - empirically consistently the last Monday of July.
      .hasFixedWeekdayHoliday("CARNIVAL_MONDAY", LAST, MONDAY, JULY).inSubdivision("sa").and()
      // "de eerste vrijdag van december" (Regeling feestdagen ambtenaren BES, Art. 3) - a floating
      // first-Friday-of-December rule, NOT the fixed 6 December date that Wikipedia's "Public
      // holidays in Saba" article incorrectly lists.
      .hasFixedWeekdayHoliday("SABA_DAY", FIRST, FRIDAY, DECEMBER).inSubdivision("sa")
      .check();
  }
}
