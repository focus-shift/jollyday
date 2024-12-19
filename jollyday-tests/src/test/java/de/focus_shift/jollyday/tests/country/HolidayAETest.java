package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_ARAB_EMIRATES;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

class HolidayAETest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(UNITED_ARAB_EMIRATES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AE_COMMEMORATION_DAY", DECEMBER, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 2).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 3).between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN_END").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_3").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ARAFAAT").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_3").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
