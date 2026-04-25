package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MAURITANIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayMRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(MAURITANIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AFRICA_DAY", MAY, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REVOLUTION", JULY, 10).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
