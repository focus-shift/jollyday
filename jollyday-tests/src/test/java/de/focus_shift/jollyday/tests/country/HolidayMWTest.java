package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MALAWI;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayMWTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(MALAWI)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("JOHN_CHILEMBWE", JANUARY, 15).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("MARTYRS_DAY", MARCH, 3).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("KAMUZU_BANDA", MAY, 14).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 6).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("MOTHERS", OCTOBER, 15).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).canBeMovedFrom(SUNDAY, MONDAY).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
