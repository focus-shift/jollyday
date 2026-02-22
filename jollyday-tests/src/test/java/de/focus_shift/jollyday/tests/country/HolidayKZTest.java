package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.KAZAKHSTAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;

class HolidayKZTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(KAZAKHSTAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SPRING_DAY", MARCH, 22).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY", MAY, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REPUBLIC_DAY", JULY, 6).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", AUGUST, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 16).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
