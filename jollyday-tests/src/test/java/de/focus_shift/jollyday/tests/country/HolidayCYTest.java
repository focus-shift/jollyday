package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.CYPRUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayCYTest {

  @Test
  void ensuresHolidays() {
    assertFor(CYPRUS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("GREEK_INDEPENDENCE_DAY", MARCH, 25).and()
      .hasFixedHoliday("CYPRUS_NATIONAL_DAY", APRIL, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VIRGIN_MARY", AUGUST, 15).and()
      .hasFixedHoliday("CYPRUS_INDEPENDENCE_DAY", OCTOBER, 1).and()
      .hasFixedHoliday("OCHI", OCTOBER, 28).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("CLEAN_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("EASTER_TUESDAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
