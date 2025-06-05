package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static de.focus_shift.jollyday.core.HolidayCalendar.URUGUAY;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayUYTest {

  @Test
  void ensuresHolidays() {
    assertFor(URUGUAY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LANDING_33_EASTERNERS", APRIL, 19)
        .canBeShiftedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("LAS_PIEDRAS", MAY, 18)
        .canBeShiftedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("ARTIGAS", JUNE, 19).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 18).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 25).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("RACE", OCTOBER, 12)
        .canBeShiftedFrom(DayOfWeek.TUESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.WEDNESDAY, PREVIOUS, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.THURSDAY, DayOfWeek.MONDAY)
        .canBeShiftedFrom(DayOfWeek.FRIDAY, DayOfWeek.MONDAY)
      .and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("SHROVE_MONDAY").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
