package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.LONDON_METAL_EXCHANGE;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;

class HolidayLMETest {

  @Test
  void ensuresHolidays() {
    assertFor(LONDON_METAL_EXCHANGE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BANK_HOLIDAY", MAY, 8)
        .notValidBetween(Year.of(1900), Year.of(2019))
        .validBetween(Year.of(2020), Year.of(2020))
        .notValidBetween(Year.of(2021), Year.of(2500))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .notValidBetween(Year.of(1900), Year.of(2022))
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2024), Year.of(2500))
      .and()
      .hasFixedHoliday("BANK_HOLIDAY", JUNE, 4)
        .notValidBetween(Year.of(1900), Year.of(2011))
        .validBetween(Year.of(2012), Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2500))
      .and()
      .hasFixedHoliday("BANK_HOLIDAY", JUNE, 2)
        .notValidBetween(Year.of(1900), Year.of(2021))
        .validBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2023), Year.of(2500))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .notValidBetween(Year.of(1900), Year.of(2021))
        .validBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2023), Year.of(2500))
      .and()
      .hasFixedWeekdayHoliday("BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validTo(Year.of(2019))
        .validFrom(Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2011))
        .validBetween(Year.of(2013), Year.of(2021))
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("BANK_HOLIDAY", LAST, MONDAY, AUGUST)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
