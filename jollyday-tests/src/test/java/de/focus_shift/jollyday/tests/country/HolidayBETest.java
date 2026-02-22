package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BELGIUM;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayBETest {

  @Test
  void ensuresHolidays() {
    assertFor(BELGIUM)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 21).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2, OBSERVANCE).and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11).and()
      .hasFixedHoliday("KINGS_FEAST", NOVEMBER, 15, OBSERVANCE).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("PENTECOST_MONDAY")
      .check();
  }
}

