package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NAURU;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayNRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(NAURU)
      // Public Service Act 2016, s. 81: falls on Saturday or Sunday -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Independence Day and Christmas Day are the two exceptions: when they fall on the
      // weekend, both the following Monday and Tuesday become holidays (see ensuresFloatingHolidays)
      .hasFixedHoliday("INDEPENDENCE_DAY", JANUARY, 31)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_INDEPENDENCE_DAY", FEBRUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Declared by President Marcus Stephen from 8 March 2010
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .notValidBetween(YEAR_FROM, Year.of(2009))
        .validBetween(Year.of(2010), YEAR_TO)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 17)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("RONPHOS_HANDOVER_DAY", JULY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Gazetted as a new annual public holiday from 2019
      .hasFixedHoliday("IBUMIN_EAROENI_DAY", AUGUST, 19)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .notValidBetween(YEAR_FROM, Year.of(2018))
        .validBetween(Year.of(2019), YEAR_TO)
      .and()
      // Renamed to Sir Hammer DeRoburt Day from 2020
      .hasFixedHoliday("NATIONAL_YOUTH_DAY", SEPTEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2009), Year.of(2019))
        .notValidBetween(Year.of(2020), YEAR_TO)
      .and()
      .hasFixedHoliday("SIR_HAMMER_DEROBURT_DAY", SEPTEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .notValidBetween(YEAR_FROM, Year.of(2019))
        .validBetween(Year.of(2020), YEAR_TO)
      .and()
      .hasFixedHoliday("ANGAM_DAY", OCTOBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_TUESDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
