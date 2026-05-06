package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.KYRGYZSTAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayKGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(KYRGYZSTAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ORTHODOX_CHRISTMAS", JANUARY, 7).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DEFENDER_FATHERLAND", FEBRUARY, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("APRIL_REVOLUTION", APRIL, 7).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY", MAY, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAYS_OF_HISTORY", NOVEMBER, 7).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAYS_OF_HISTORY", NOVEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}

