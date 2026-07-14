package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MARSHALL_ISLANDS;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayMHTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(MARSHALL_ISLANDS)
      // Public Holidays Act 1988, 1 MIRC Ch.9, §902: Saturday -> preceding Friday, Sunday -> following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("NUCLEAR_VICTIMS_REMEMBRANCE_DAY", MARCH, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("PRESIDENTS_DAY", NOVEMBER, 17)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedWeekdayHoliday("FISHERMENS_DAY", FIRST, FRIDAY, JULY).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, FRIDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("MANIT_DAY", LAST, FRIDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("GOSPEL_DAY", FIRST, FRIDAY, DECEMBER)
      .check();
  }
}
