package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.PERU;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayPETest {

  @Test
  void ensuresHolidays() {
    assertFor(PERU)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 28).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 29).and()
      .hasFixedHoliday("SANTA_ROSA", AUGUST, 30).and()
      .hasFixedHoliday("ANGAMOS", OCTOBER, 8).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}

