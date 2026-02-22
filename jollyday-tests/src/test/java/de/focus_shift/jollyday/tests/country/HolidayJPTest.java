package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.JAPAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayJPTest {

  @Test
  void ensuresHolidays() {
    assertFor(JAPAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(Year.of(1948)).and()
      .hasFixedHoliday("COMING_OF_AGE", JANUARY, 15)
        .validBetween(Year.of(1948), Year.of(1999)).and()
      .hasFixedHoliday("FOUNDATION", FEBRUARY, 11)
        .validFrom(Year.of(1967)).and()
      .hasFixedHoliday("SHOWA_DAY", APRIL, 29).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 3)
        .validFrom(Year.of(1948)).and()
      .hasFixedHoliday("GREENERY_DAY", MAY, 4)
        .validFrom(Year.of(2007)).and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validFrom(Year.of(1948)).and()
      .hasFixedHoliday("NAVY_DAY", JULY, 20)
        .validBetween(Year.of(1996), Year.of(2002)).and()
      .hasFixedHoliday("RESPECT_AGED_DAY", SEPTEMBER, 15)
        .validBetween(Year.of(1966), Year.of(2002)).and()
      .hasFixedHoliday("HEALTH_SPORTS", OCTOBER, 10)
        .validBetween(Year.of(1966), Year.of(1999)).and()
      .hasFixedHoliday("CULTURE_DAY", NOVEMBER, 3)
        .canBeMovedFrom(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
        .validFrom(Year.of(1948))
      .and()
      .hasFixedHoliday("LABOUR_DAY", NOVEMBER, 23)
        .validFrom(Year.of(1948)).and()
      .hasFixedHoliday("EMPERORS_BIRTHDAY", DECEMBER, 23)
        .validFrom(Year.of(1990)).and()
      .hasFixedHoliday("IMPERIAL_DAY", APRIL, 10)
        .validBetween(Year.of(1959), Year.of(1959)).and()
      .hasFixedHoliday("IMPERIAL_DAY", FEBRUARY, 24)
        .validBetween(Year.of(1989), Year.of(1989)).and()
      .hasFixedHoliday("IMPERIAL_DAY", NOVEMBER, 12)
        .validBetween(Year.of(1990), Year.of(1990)).and()
      .hasFixedHoliday("IMPERIAL_DAY", JUNE, 9)
        .validBetween(Year.of(1993), Year.of(1993)).and()
      .hasFixedHoliday("MOUNTAIN_DAY", AUGUST, 11)
        .validFrom(Year.of(2016))
      .check();
  }
}

