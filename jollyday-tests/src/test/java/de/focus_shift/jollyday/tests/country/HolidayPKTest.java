package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PAKISTAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;

class HolidayPKTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(PAKISTAN)
      .hasFixedHoliday("KASHMIR_DAY", FEBRUARY, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("PAKISTAN_DAY", MARCH, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("YEOM_E_TAKBEER", MAY, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("IQBAL_DAY", NOVEMBER, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("QUAIDE_AZAM_DAY", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
