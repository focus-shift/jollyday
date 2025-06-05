package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SLOWENIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidaySITest {

  @Test
  void ensuresSlovenianHolidays() {
    assertFor(SLOWENIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .between(Year.of(1955), Year.of(2012))
         .notBetween(Year.of(2013), Year.of(2016))
        .between(Year.of(2017), Year.of(2500))
      .and()
      .hasFixedHoliday("PRESEREN", FEBRUARY, 8)
        .notBetween(Year.of(1900), Year.of(1990))
        .between(Year.of(1991), Year.of(2500))
      .and()
      .hasFixedHoliday("LIBERATION", APRIL, 27).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .notBetween(Year.of(1900), Year.of(1948))
        .between(Year.of(1949), Year.of(2500))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2)
        .notBetween(Year.of(1900), Year.of(1948))
        .between(Year.of(1949), Year.of(2500))
      .and()
      .hasFixedHoliday("STATEHOOD", JUNE, 25).and()
      .hasFixedHoliday("SOLIDARITY_DAY", AUGUST, 14)
        .notBetween(Year.of(1900), Year.of(2022))
        .between(Year.of(2023), Year.of(2023))
        .notBetween(Year.of(2024), Year.of(2055))
      .and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15)
        .notBetween(Year.of(1900), Year.of(1991))
        .between(Year.of(1992), Year.of(2500))
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .notBetween(Year.of(1900), Year.of(1991))
        .between(Year.of(1992), Year.of(2500))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .between(Year.of(1900), Year.of(1952))
        .notBetween(Year.of(1953), Year.of(1990))
        .between(Year.of(1991), Year.of(2500))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("PENTECOST")
      .check();
  }
}
