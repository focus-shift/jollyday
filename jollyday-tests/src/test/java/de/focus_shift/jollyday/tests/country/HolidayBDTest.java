package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BANGLADESH;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;

class HolidayBDTest {

  private static final Year YEAR_FROM = Year.of(2020);
  private static final Year YEAR_TO = Year.of(2030);

  @Test
  void ensuresHolidays() {
    assertFor(BANGLADESH)
      .hasFixedHoliday("MOTHER_LANGUAGE_DAY", FEBRUARY, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_AND_NATIONAL_DAY", MARCH, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BENGALI_NEW_YEAR", APRIL, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("JULY_MASS_UPRISING_DAY", AUGUST, 5).validBetween(Year.of(2025), YEAR_TO).and()
      .hasFixedHoliday("VICTORY", DECEMBER, 16).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("JUMUATUL_WIDA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ARAFAAT").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_3").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
