package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAUDI_ARABIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.SEPTEMBER;

class HolidaySATest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(SAUDI_ARABIA)
      .hasFixedHoliday("NATIONAL_DAY", SEPTEMBER, 23).between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN_END").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_3").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ARAFAAT").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_3").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}

