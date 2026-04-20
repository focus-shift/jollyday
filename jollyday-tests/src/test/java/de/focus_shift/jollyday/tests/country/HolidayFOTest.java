package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.FAROE_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;

class HolidayFOTest {

  @Test
  void ensuresHolidays() {
    assertFor(FAROE_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("FLAG_DAY", APRIL, 25).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JUNE, 5).and()
      .hasFixedHoliday("SAINT_OLAV_EVE", JULY, 28).and()
      .hasFixedHoliday("SAINT_OLAV_DAY", JULY, 29).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasChristianHoliday("GENERAL_PRAYER_DAY")
      .check();
  }
}
