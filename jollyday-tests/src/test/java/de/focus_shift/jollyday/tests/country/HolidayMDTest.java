package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.MOLDOVA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Year.of;

class HolidayMDTest {

  @Test
  void ensuresMoldovaHolidays() {
    assertFor(MOLDOVA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CHRISTMAS_EVE", JANUARY, 7).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 8).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .between(of(1977), of(2500)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .between(of(1965), of(2500)).and()
      .hasFixedHoliday("CHILDRENS_DAY", JUNE, 1)
        .between(of(2016), of(2500)).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 27)
        .between(of(1991), of(2500)).and()
      .hasFixedHoliday("LANGUAGE_DAY", AUGUST, 31)
        .between(of(1990), of(2500)).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
