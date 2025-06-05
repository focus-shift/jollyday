package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UKRAINE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayUATest {

  @Test
  void ensuresHolidays() {

    assertFor(UKRAINE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2)
        .between(Year.of(1900), Year.of(2017))
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 3)
        .between(Year.of(2016), Year.of(2016))
      .and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JUNE, 28)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 24)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("DEFENDER_OF_UKRAINE", OCTOBER, 14)
        .notBetween(Year.of(1900), Year.of(2014))
        .between(Year.of(2015), Year.of(2050))
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .notBetween(Year.of(1900), Year.of(2016))
        .between(Year.of(2017), Year.of(2050))
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("EASTER")
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("PENTECOST")
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .check();
  }
}
