package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PALAU;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayPWTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(PALAU)
      // Palau National Code, Chapter 7, Section 701: Saturday -> preceding Friday, Sunday -> following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("YOUTH_DAY", MARCH, 15)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("SENIOR_CITIZENS_DAY", MAY, 5)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // No record of being a legal holiday before 2017 (RPPL No. 10-15); assumed to start 2018
      .hasFixedHoliday("PRESIDENTS_DAY", JUNE, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .notValidBetween(YEAR_FROM, Year.of(2017))
        .validBetween(Year.of(2018), YEAR_TO)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 9)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .notValidBetween(YEAR_FROM, Year.of(2017))
        .validBetween(Year.of(2018), YEAR_TO)
      .and()
      .hasFixedHoliday("UNITED_NATIONS_DAY", OCTOBER, 24)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // One-off public holiday declared for the 2020 U.S. presidential election
      .hasFixedHoliday("NATIONAL_DAY_OF_DEMOCRACY", NOVEMBER, 3).validBetween(of(2020), of(2020)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Repealed by Executive Order 336 after 2012.
      .hasFixedWeekdayHoliday("MEMORIAL_DAY", LAST, MONDAY, MAY)
        .notValidBetween(YEAR_FROM, Year.of(2010))
        .validBetween(Year.of(2011), Year.of(2012))
        .notValidBetween(Year.of(2013), YEAR_TO)
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER).and()
      // Established by Republic of Palau Public Law No. 10-15.
      .hasFixedWeekdayHoliday("FAMILY_DAY", FOURTH, FRIDAY, NOVEMBER)
        .notValidBetween(YEAR_FROM, Year.of(2016))
        .validFrom(Year.of(2017))
      .check();
  }
}
