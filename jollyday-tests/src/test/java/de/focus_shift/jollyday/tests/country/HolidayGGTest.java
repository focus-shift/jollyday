package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GUERNSEY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayGGTest extends AbstractCountryTestBase {

  @Test
  void ensuresHolidays() {
    assertFor(GUERNSEY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .and()
      .hasFixedHoliday("LIBERATION", MAY, 10)
        .notValidBetween(Year.of(1900), Year.of(2009))
        .validBetween(Year.of(2010), Year.of(2010))
        .notValidBetween(Year.of(2011), Year.of(2500))
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 9)
        .validBetween(Year.of(1900), Year.of(2009))
        .notValidBetween(Year.of(2010), Year.of(2010))
        .validBetween(Year.of(2011), Year.of(2500))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .and()
      .hasFixedHoliday("MAY_DAY_BANK_HOLIDAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_STATE_FUNERAL", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }

  @ParameterizedTest
  @ValueSource(strings = {"2010", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"})
  void testManagerGGStructure(final Year year) {
    validateCalendarData(GUERNSEY.getId(), year, true);
  }
}

