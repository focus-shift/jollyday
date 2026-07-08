package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PORTUGAL;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayPTTest {
  @Test
  void ensuresHolidays() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", APRIL, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", JUNE, 10).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("REPUBLIC_DAY", OCTOBER, 5)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 1)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CARNIVAL", OBSERVANCE).and()
      .hasChristianHoliday("CORPUS_CHRISTI")
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasChristianHoliday("AZORES_DAY", true)
        .inSubdivision("20")
      .and()
      .hasFixedHoliday("AUTONOMY_DAY", APRIL, 2)
        .inSubdivision("30")
        .notValidBetween(Year.of(1900), Year.of(2024))
        .validFrom(Year.of(2025))
      .and()
      .hasFixedHoliday("MADEIRA_DAY", JULY, 1)
        .inSubdivision("30")
      .and()
      .hasFixedHoliday("FIRST_OCTAVE", DECEMBER, 26)
        .inSubdivision("30")
      .check();
  }
}
