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
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 3)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 4)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 5)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 6)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 9)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEFENDER_FATHERLAND", FEBRUARY, 23)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 12)
        .notValidBetween(Year.of(1900), Year.of(1991))
        .validFrom(Year.of(1992))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CONSOLIDATION", NOVEMBER, 4)
        .notValidBetween(Year.of(1900), Year.of(2004))
        .validFrom(Year.of(2005))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }
}
