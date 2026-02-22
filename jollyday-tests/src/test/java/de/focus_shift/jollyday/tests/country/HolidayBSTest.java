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
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
      .and()
      .hasFixedHoliday("MAJORITY_RULE_DAY", JANUARY, 10)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .notValidBetween(Year.of(1900), Year.of(2013))
        .validFrom(Year.of(2014))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 10)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .validFrom(Year.of(1973))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
