package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.REPUBLIC_OF_KOREA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayKRTest {

  @Test
  void ensuresSolarHolidays() {
    assertFor(REPUBLIC_OF_KOREA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(1949), Year.of(2035))
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .validBetween(Year.of(1949), Year.of(1998))
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 3)
        .validBetween(Year.of(1949), Year.of(1989))
      .and()
      .hasFixedHoliday("INDEPENDENCE_MOVEMENT_DAY", MARCH, 1)
        .validBetween(Year.of(1949), Year.of(2020))
      .and()
      .hasFixedHoliday("INDEPENDENCE_MOVEMENT_DAY", MARCH, 1)
        .validBetween(Year.of(2021), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ARBOR", APRIL, 5)
        .validBetween(Year.of(1949), Year.of(2005))
      .and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validBetween(Year.of(1975), Year.of(2013))
      .and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validBetween(Year.of(2014), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", JUNE, 6)
        .validBetween(Year.of(1956), Year.of(2035))
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 17)
        .validBetween(Year.of(1950), Year.of(2007))
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 17)
        .validBetween(Year.of(2026), Year.of(2035))
      .and()
      .hasFixedHoliday("LIBERATION_DAY", AUGUST, 15)
        .validBetween(Year.of(1949), Year.of(2020))
      .and()
      .hasFixedHoliday("LIBERATION_DAY", AUGUST, 15)
        .validBetween(Year.of(2021), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ARMED_FORCES_DAY", OCTOBER, 1)
        .validBetween(Year.of(1976), Year.of(1990))
      .and()
      .hasFixedHoliday("NATIONAL_FOUNDATION_DAY", OCTOBER, 3)
        .validBetween(Year.of(1949), Year.of(2020))
      .and()
      .hasFixedHoliday("NATIONAL_FOUNDATION_DAY", OCTOBER, 3)
        .validBetween(Year.of(2021), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("HANGEUL_DAY", OCTOBER, 9)
        .validBetween(Year.of(1949), Year.of(1990))
      .and()
      .hasFixedHoliday("HANGEUL_DAY", OCTOBER, 9)
        .validBetween(Year.of(2013), Year.of(2020))
      .and()
      .hasFixedHoliday("HANGEUL_DAY", OCTOBER, 9)
        .validBetween(Year.of(2021), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(1949), Year.of(2022))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2023), Year.of(2030))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @Test
  void ensuresLunarHolidays() {
    assertFor(REPUBLIC_OF_KOREA)
      // 2023 - Seollal fell on a Sunday, substitute observed on the Tuesday after the period
      .hasFixedHoliday("DAY_PRECEDING_LUNAR_NEW_YEAR", JANUARY, 21)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", JANUARY, 22)
        .validBetween(Year.of(2023), Year.of(2023))
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_LUNAR_NEW_YEAR", JANUARY, 23)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // 2023 - Buddha's Birthday, first substitute after the May 2023 rule extension
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 27)
        .validBetween(Year.of(2023), Year.of(2023))
        .canBeMovedFrom(SATURDAY, MONDAY)
      .and()
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", SEPTEMBER, 28)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 29)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_CHUSEOK", SEPTEMBER, 30)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // 2024 - the day following Seollal fell on a Sunday
      .hasFixedHoliday("DAY_PRECEDING_LUNAR_NEW_YEAR", FEBRUARY, 9)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 10)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_LUNAR_NEW_YEAR", FEBRUARY, 11)
        .validBetween(Year.of(2024), Year.of(2024))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 15)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", SEPTEMBER, 16)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 17)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_CHUSEOK", SEPTEMBER, 18)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      // 2025 - Buddha's Birthday coincided with Children's Day, substitute on 6 May;
      // the day preceding Chuseok fell on a Sunday, substitute observed on Wednesday 8 October
      .hasFixedHoliday("DAY_PRECEDING_LUNAR_NEW_YEAR", JANUARY, 28)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", JANUARY, 29)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_LUNAR_NEW_YEAR", JANUARY, 30)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 5)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 6)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", OCTOBER, 5)
        .validBetween(Year.of(2025), Year.of(2025))
        .canBeMovedFrom(SUNDAY, WEDNESDAY)
      .and()
      .hasFixedHoliday("CHUSEOK", OCTOBER, 6)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_CHUSEOK", OCTOBER, 7)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      // 2026 - Buddha's Birthday fell on a Sunday
      .hasFixedHoliday("DAY_PRECEDING_LUNAR_NEW_YEAR", FEBRUARY, 16)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 17)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_LUNAR_NEW_YEAR", FEBRUARY, 18)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 24)
        .validBetween(Year.of(2026), Year.of(2026))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", SEPTEMBER, 24)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 25)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_CHUSEOK", SEPTEMBER, 26)
        .validBetween(Year.of(2026), Year.of(2026))
      .check();
  }
}
