package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BAHAMAS;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.DECEMBER;

class HolidayBSTest {

  @Test
  void ensuresHolidays() {
    assertFor(BAHAMAS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
        .canBeShiftedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, FRIDAY)
        .canBeShiftedFrom(THURSDAY, FRIDAY)
      .and()
      .hasFixedHoliday("MAJORITY_RULE_DAY", JANUARY, 10)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
        .canBeShiftedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, FRIDAY)
        .canBeShiftedFrom(THURSDAY, FRIDAY)
        .notBetween(Year.of(1900), Year.of(2013))
        .between(Year.of(2014), Year.of(2500))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 10)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
        .canBeShiftedFrom(TUESDAY, PREVIOUS, MONDAY)
        .between(Year.of(1973), Year.of(2500))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeShiftedFrom(SUNDAY, TUESDAY)
        .canBeShiftedFrom(MONDAY, TUESDAY)
        .canBeShiftedFrom(SATURDAY, MONDAY)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
