package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ETHIOPIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;

class HolidayETTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(ETHIOPIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY_ADWA", MARCH, 2).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY", MAY, 5).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", MAY, 28).between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").between(YEAR_FROM, YEAR_TO).and()
      .hasEthiopianOrthodoxHoliday("TIMKAT").between(YEAR_FROM, YEAR_TO).and()
      .hasEthiopianOrthodoxHoliday("ENKUTATASH").between(YEAR_FROM, YEAR_TO).and()
      .hasEthiopianOrthodoxHoliday("MESKEL").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
