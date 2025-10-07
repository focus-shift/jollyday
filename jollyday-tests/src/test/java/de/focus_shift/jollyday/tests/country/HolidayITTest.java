package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ITALY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayITTest {

  @Test
  void ensuresItalianHolidays() {
    assertFor(ITALY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1966))
        .validFrom(Year.of(1967))
      .and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LIBERATION", APRIL, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("REPUBLIC_DAY", JUNE, 2).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ST_FRANCIS_OF_ASSISI", OCTOBER, 4)
        .notValidBetween(Year.of(1900), Year.of(2025))
        .validBetween(Year.of(2026), Year.of(2500))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validFrom(Year.of(1642))
      .and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("32")
      .check();
  }
}
