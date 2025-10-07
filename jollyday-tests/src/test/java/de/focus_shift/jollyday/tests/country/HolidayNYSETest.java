package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NYSE;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.OCTOBER;

class HolidayNYSETest {

  @Test
  void ensuresHolidays() {

    assertFor(NYSE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("JUNETEENTH", JUNE, 19)
        .notValidBetween(Year.of(1900), Year.of(2021))
        .validFrom(Year.of(2022))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 4)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("FUNERAL_OF_PRESIDENT_REAGAN", JUNE, 11)
        .notValidBetween(Year.of(1900), Year.of(2003))
        .validBetween(Year.of(2004), Year.of(2004))
        .notValidBetween(Year.of(2005), Year.of(2500))
      .and()
      .hasFixedHoliday("REMEMBERANCE_OF_PRESIDENT_FORD", JANUARY, 2)
        .notValidBetween(Year.of(1900), Year.of(2006))
        .validBetween(Year.of(2007), Year.of(2007))
        .notValidBetween(Year.of(2008), Year.of(2500))
      .and()
      .hasFixedHoliday("HURRICANE_SANDY", OCTOBER, 29)
        .notValidBetween(Year.of(1900), Year.of(2011))
        .validBetween(Year.of(2012), Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2500))
      .and()
      .hasFixedHoliday("HURRICANE_SANDY", OCTOBER, 30)
        .notValidBetween(Year.of(1900), Year.of(2011))
        .validBetween(Year.of(2012), Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2500))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
