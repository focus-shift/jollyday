package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.WESTERN_SAHARA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayEHTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(WESTERN_SAHARA)
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 27).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("FIRST_MARTYR", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("POLISARIO_FRONT_FOUNDATION", MAY, 10).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("TWENTY_MAY_REVOLUTION", MAY, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("MARTYRS_DAY", JUNE, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ZEMLA_INTIFADA", JUNE, 17).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_UNITY_DAY", OCTOBER, 12).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
