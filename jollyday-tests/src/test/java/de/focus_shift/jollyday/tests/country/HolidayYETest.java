package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.YEMEN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;

class HolidayYETest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(YEMEN)
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("UNITY", MAY, 22).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REVOLUTION", SEPTEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LIBERATION_DAY", OCTOBER, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_3").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_3").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
