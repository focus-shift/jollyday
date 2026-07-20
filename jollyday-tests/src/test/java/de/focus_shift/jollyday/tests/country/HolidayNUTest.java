package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NIUE;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.OCTOBER;

class HolidayNUTest {

  @Test
  void ensuresHolidays() {
    assertFor(NIUE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("TAKAI_COMMISSION_HOLIDAY", JANUARY, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("ANZAC", APRIL, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", OCTOBER, 19)
        .validFrom(Year.of(1974))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY_HOLIDAY", OCTOBER, 20)
        .validFrom(Year.of(1974))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedWeekdayHoliday("ANNEXATION_DAY", THIRD, MONDAY, OCTOBER)
        .validTo(Year.of(1973))
      .and()
      .hasFixedWeekdayHoliday("PENIAMINA_GOSPEL_DAY", FOURTH, MONDAY, OCTOBER)
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", FIRST, MONDAY, JUNE)
        .validTo(Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", FIRST, MONDAY, JUNE)
        .validFrom(Year.of(2023))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
