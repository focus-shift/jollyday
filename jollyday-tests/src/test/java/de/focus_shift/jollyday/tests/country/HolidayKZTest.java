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
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SPRING_DAY", MARCH, 22).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY", MAY, 9).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REPUBLIC_DAY", JULY, 6).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", AUGUST, 30).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 16).between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
