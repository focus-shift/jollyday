package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.RUSSIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayRUTest {

  @Test
  void ensuresHolidays() {
    assertFor(RUSSIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 3)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 4)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 5)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 6)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 8)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 9)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEFENDER_FATHERLAND", FEBRUARY, 23)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 12)
        .notBetween(Year.of(1900), Year.of(1991))
        .between(Year.of(1992), Year.of(2500))
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CONSOLIDATION", NOVEMBER, 4)
        .notBetween(Year.of(1900), Year.of(2004))
        .between(Year.of(2005), Year.of(2500))
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .check();
  }
}
