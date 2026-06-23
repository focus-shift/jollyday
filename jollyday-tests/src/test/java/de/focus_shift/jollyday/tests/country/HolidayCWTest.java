package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.CURACAO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayCWTest {

  @Test
  void ensuresHolidays() {
    assertFor(CURACAO)
      // Fixed holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("KINGS_DAY", APRIL, 27).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("CURACAO_NATIONAL_FLAG_AND_ANTHEM_DAY", JULY, 2).and()
      .hasFixedHoliday("CURACAO_DAY", OCTOBER, 10).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_DAY_OF_CHRISTMAS", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("CARNIVAL_MONDAY", true).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_SUNDAY")
    .check();
  }
}
