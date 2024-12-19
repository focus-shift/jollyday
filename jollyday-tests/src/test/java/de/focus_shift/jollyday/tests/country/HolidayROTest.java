package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ROMANIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayROTest {

  @Test
  void ensuresHolidays() {

    assertFor(ROMANIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .notBetween(Year.of(1900), Year.of(2023))
        .between(Year.of(2024), Year.of(2500))
      .and()
      .hasFixedHoliday("ST_JOHN", JANUARY, 7)
        .notBetween(Year.of(1900), Year.of(2023))
        .between(Year.of(2024), Year.of(2500))
      .and()
      .hasFixedHoliday("UNIFICATION", JANUARY, 24)
        .notBetween(Year.of(1900), Year.of(2016))
        .between(Year.of(2017), Year.of(2500))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("CHILDRENS_DAY", JUNE, 1)
        .notBetween(Year.of(1900), Year.of(2016))
        .between(Year.of(2017), Year.of(2500))
      .and()
      .hasFixedHoliday("NAVY_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ST_ANDREW", NOVEMBER, 30).and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .notBetween(Year.of(1900), Year.of(2017))
        .between(Year.of(2018), Year.of(2500))
      .and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
