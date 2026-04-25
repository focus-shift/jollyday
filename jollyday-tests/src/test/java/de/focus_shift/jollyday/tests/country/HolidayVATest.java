package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.VATICAN_CITY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
class HolidayVATest {

  @Test
  void ensuresVaticanCityHolidays() {
    assertFor(VATICAN_CITY)
      .hasFixedHoliday("SOLEMNITY_MARY_MOTHER", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("FOUNDATION_ANNIVERSARY", FEBRUARY, 11).and()
      .hasFixedHoliday("PETER_FRANCIS_ELECTION", MARCH, 13)
        .validBetween(Year.of(2014), Year.of(2025))
      .and()
      .hasFixedHoliday("LEO_XIV_ELECTION", MAY, 8)
        .validFrom(Year.of(2026))
      .and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).and()
      .hasFixedHoliday("ST_GEORGE", APRIL, 23).and()
      .hasFixedHoliday("ST_JOSEPH_WORKER", MAY, 1).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ST_ROBERT_BELLARMINE", NOVEMBER, 17).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
