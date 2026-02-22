package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ESTONIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;

class HolidayEETest {

  @Test
  void ensuresHolidays() {
    assertFor(ESTONIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 24).and()
      .hasFixedHoliday("SPRING_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY", JUNE, 23).and()
      .hasFixedHoliday("MIDSUMMER", JUNE, 24).and()
      .hasFixedHoliday("REST_INDEP", AUGUST, 20)
        .notValidBetween(Year.of(1900), Year.of(1990))
        .validFrom(Year.of(1991))
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("PENTECOST")
      .check();
  }
}
