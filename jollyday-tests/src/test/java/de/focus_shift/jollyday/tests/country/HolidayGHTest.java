package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GHANA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayGHTest {

  private static final Year YEAR_FROM = Year.of(1957);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(GHANA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JANUARY, 7)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MARCH, 6)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("REPUBLIC_DAY", JULY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("FOUNDERS_DAY", SEPTEMBER, 21)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.SUNDAY, NEXT, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(DayOfWeek.MONDAY, NEXT, DayOfWeek.TUESDAY)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
