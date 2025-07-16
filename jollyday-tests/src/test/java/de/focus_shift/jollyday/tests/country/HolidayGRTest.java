package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GREECE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.DECEMBER;

class HolidayGRTest {

  @Test
  void ensuresHolidays() {
    assertFor(GREECE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("THEOPHANY", JANUARY, 6).and()
      .hasFixedHoliday("THREE_HIERARCHS", JANUARY, 30).and()
      .hasFixedHoliday("ANNUNCIATION", MARCH, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .to(Year.of(2023))
        .notBetween(Year.of(2024), Year.of(2024))
        .from(Year.of(2025)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 7)
        .notBetween(Year.of(1900), Year.of(2023))
        .between(Year.of(2024), Year.of(2024))
        .notBetween(Year.of(2025), Year.of(2500)).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("OCHI", OCTOBER, 28).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CLEAN_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}

