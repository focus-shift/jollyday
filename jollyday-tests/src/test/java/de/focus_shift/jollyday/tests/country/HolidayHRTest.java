package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.CROATIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayHRTest {

  @Test
  void ensuresHolidays() {

    assertFor(CROATIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", MAY, 30)
        .notValidBetween(Year.of(1900), Year.of(2019))
        .validFrom(Year.of(2020))
      .and()
      .hasFixedHoliday("ANTI_FASCIST", JUNE, 22).and()
      .hasFixedHoliday("STATEHOOD", JUNE, 25)
        .notValidBetween(Year.of(1900), Year.of(2001))
        .validBetween(Year.of(2002), Year.of(2019))
        .notValidBetween(Year.of(2020), Year.of(2500))
      .and()
      .hasFixedHoliday("VICTORY", AUGUST, 5).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 8)
        .notValidBetween(Year.of(1900), Year.of(2001))
        .validBetween(Year.of(2002), Year.of(2019))
        .notValidBetween(Year.of(2020), Year.of(2500))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("REMEMBRANCE", NOVEMBER, 18)
        .notValidBetween(Year.of(1900), Year.of(2019))
        .validFrom(Year.of(2020))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }

}
