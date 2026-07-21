package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static de.focus_shift.jollyday.core.HolidayCalendar.DOMINICAN_REPUBLIC;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayDOTest {

  @Test
  void ensuresHolidays() {
    assertFor(DOMINICAN_REPUBLIC)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .canBeMovedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("OUR_LADY_OF_ALTAGRACIA", JANUARY, 21).and()
      .hasFixedHoliday("DUARTE", JANUARY, 26)
        .canBeMovedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 27).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("RESTORATION_DAY", AUGUST, 16).and()
      .hasFixedHoliday("OUR_LADY_OF_MERCEDES", SEPTEMBER, 24).and()
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 6)
        .canBeMovedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeMovedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
