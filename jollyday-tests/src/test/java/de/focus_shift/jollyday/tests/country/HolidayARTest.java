package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARGENTINA;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayARTest {

  @Test
  void ensuresHolidays() {
    assertFor(ARGENTINA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("REMEMBRANCE_TRUTH_JUSTICE", MARCH, 24)
        .notValidBetween(Year.of(1900), Year.of(2005))
        .validFrom(Year.of(2006)).and()
      .hasFixedHoliday("MALVINAS", APRIL, 2)
        .notValidBetween(Year.of(1900), Year.of(2000))
        .validFrom(Year.of(2001)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("MAY_REVOLUTION", MAY, 25).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 9).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, NEXT, MONDAY)
        .canBeMovedFrom(FRIDAY, NEXT, MONDAY)
        .canBeMovedFrom(SATURDAY, NEXT, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
