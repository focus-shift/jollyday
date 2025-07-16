package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SOUTH_AFRICA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayZATest {
  @Test
  void ensuresHolidays() {
    assertFor(SOUTH_AFRICA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("HUMAN_RIGHTS", MARCH, 21)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("RIEBEECK", APRIL, 6)
        .between(of(1952), of(1974)).and()
      .hasFixedHoliday("FOUNDATION", APRIL, 6)
        .between(of(1980), of(1994)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .between(of(1990), of(2500)).and()
      .hasFixedHoliday("EMPIRE", MAY, 24)
        .between(of(1910), of(1951)).and()
      .hasFixedHoliday("REPUBLIC_DAY", MAY, 31)
        .between(of(1910), of(1993)).and()
      .hasFixedHoliday("FREEDOM", APRIL, 27)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .between(of(1990), of(2500))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("YOUTH", JUNE, 16)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", AUGUST, 9)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("HERITAGE", SEPTEMBER, 24)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("KRUGER", OCTOBER, 10)
        .between(of(1952), of(1993))
      .and()
      .hasFixedHoliday("GOODWILL", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
        .between(of(1910), of(1993))
      .check();
  }
}
