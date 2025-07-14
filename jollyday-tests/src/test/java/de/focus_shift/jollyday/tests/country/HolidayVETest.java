package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.VENEZUELA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayVETest {
  @Test
  void ensuresHolidays() {
    assertFor(VENEZUELA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).and()
      .hasFixedHoliday("REPUBLIC_DAY", APRIL, 19).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("CARABOBO", JUNE, 24).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 5).and()
      .hasFixedHoliday("NAVY_DAY", JULY, 24).and()
      .hasFixedHoliday("FLAG_DAY", AUGUST, 3)
        .notBetween(Year.of(1900), Year.of(2005))
        .between(Year.of(2006), Year.of(2500))
      .and()
      .hasFixedHoliday("INDIGENOUS_RESISTANCE", OCTOBER, 12).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("SHROVE_MONDAY").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}

