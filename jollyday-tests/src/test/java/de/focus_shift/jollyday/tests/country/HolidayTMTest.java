package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TURKMENISTAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayTMTest {

  private static final Year YEAR_FROM = Year.of(1991);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(TURKMENISTAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("NOWRUZ", MARCH, 21)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("NOWRUZ", MARCH, 22)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("STATE_FLAG_AND_CONSTITUTION_DAY", MAY, 18)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 27)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("DAY_OF_REMEMBRANCE", OCTOBER, 6)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("DAY_OF_NEUTRALITY", DECEMBER, 12)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
