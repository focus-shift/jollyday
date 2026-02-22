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
        .validBetween(Year.of(1955), Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2016))
        .validFrom(Year.of(2017))
      .and()
      .hasFixedHoliday("PRESEREN", FEBRUARY, 8)
        .notValidBetween(Year.of(1900), Year.of(1990))
        .validFrom(Year.of(1991))
      .and()
      .hasFixedHoliday("LIBERATION", APRIL, 27).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .notValidBetween(Year.of(1900), Year.of(1948))
        .validFrom(Year.of(1949))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2)
        .notValidBetween(Year.of(1900), Year.of(1948))
        .validFrom(Year.of(1949))
      .and()
      .hasFixedHoliday("STATEHOOD", JUNE, 25).and()
      .hasFixedHoliday("SOLIDARITY_DAY", AUGUST, 14)
        .notValidBetween(Year.of(1900), Year.of(2022))
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2024), Year.of(2055))
      .and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15)
        .notValidBetween(Year.of(1900), Year.of(1991))
        .validFrom(Year.of(1992))
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .notValidBetween(Year.of(1900), Year.of(1991))
        .validFrom(Year.of(1992))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validTo(Year.of(1952))
        .notValidBetween(Year.of(1953), Year.of(1990))
        .validFrom(Year.of(1991))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("PENTECOST")
      .check();
  }
}
