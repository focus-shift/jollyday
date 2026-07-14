package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GRENADA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayGDTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(GRENADA)
      // Bank Holidays Act, Chapter 25: falls on a Sunday -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 7)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Fixed to 1 August from 2025 onward; previously the first Monday in August (see ensuresFloatingHolidays)
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2025), YEAR_TO)
      .and()
      // Superseded by the fixed 1 August date from 2025 onward (above).
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST)
        .validTo(Year.of(2024))
        // Not a general absence claim: from 2026 onward the fixed 1 August date can coincide
        // with the first Monday in August (e.g. 2027), so only the single verified year is asserted.
        .notValidBetween(Year.of(2025), Year.of(2025))
      .and()
      // Introduced 2023, commemorating the killing of PM Maurice Bishop on 19 October 1983
      .hasFixedHoliday("NATIONAL_HEROES_DAY", OCTOBER, 19)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2023), YEAR_TO)
        .notValidBetween(YEAR_FROM, Year.of(2022))
      .and()
      .hasFixedHoliday("THANKSGIVING", OCTOBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("CORPUS_CHRISTI").validBetween(YEAR_FROM, YEAR_TO).and()
      // Spicemas: modeled as two full-day holidays, matching how it's actually observed.
      .hasFixedWeekdayHoliday("CARNIVAL_MONDAY", SECOND, MONDAY, AUGUST).and()
      .hasFixedWeekdayHoliday("CARNIVAL_TUESDAY", SECOND, TUESDAY, AUGUST)
      .check();
  }
}
