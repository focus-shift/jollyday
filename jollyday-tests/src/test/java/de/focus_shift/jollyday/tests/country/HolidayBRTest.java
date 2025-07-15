package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BRAZIL;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayBRTest {

  @Test
  void ensuresHolidays() {
    assertFor(BRAZIL)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("TIRADENTES", APRIL, 21).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 7).and()
      .hasFixedHoliday("APARECIDA", OCTOBER, 12)
        .notBetween(Year.of(1900), Year.of(1979))
        .between(Year.of(1980), Year.of(2500))
      .and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("NATIONAL_DAY", NOVEMBER, 15).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_ZUMBI_AND_BLACK_AWARENESS", NOVEMBER, 20)
        .notBetween(Year.of(1900), Year.of(2023))
        .between(Year.of(2024), Year.of(2500))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("ASH_WEDNESDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}

