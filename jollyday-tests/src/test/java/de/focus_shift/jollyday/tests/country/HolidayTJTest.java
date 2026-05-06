package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TAJIKISTAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.NOVEMBER;

class HolidayTJTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(TAJIKISTAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 22).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 24).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY", MAY, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_UNITY_DAY", JUNE, 27).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 6).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}

