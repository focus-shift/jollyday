package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayType;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.REPUBLIC_OF_KOREA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
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
        .validBetween(Year.of(2021), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ARBOR", APRIL, 5)
        .validBetween(Year.of(1949), Year.of(2005))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1, HolidayType.BANK_HOLIDAY)
        .validBetween(Year.of(1994), Year.of(2025))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validBetween(Year.of(2026), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validBetween(Year.of(1975), Year.of(2013))
      .and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 5)
        .validBetween(Year.of(2014), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", JUNE, 6)
        .validBetween(Year.of(1956), Year.of(2035))
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 17)
        .validBetween(Year.of(1949), Year.of(2007))
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 17)
        .validBetween(Year.of(2026), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LIBERATION_DAY", AUGUST, 15)
        .validBetween(Year.of(1949), Year.of(2020))
      .and()
      .hasFixedHoliday("LIBERATION_DAY", AUGUST, 15)
        .validBetween(Year.of(2021), Year.of(2035))
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
        .validBetween(Year.of(2021), Year.of(2035))
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
        .validBetween(Year.of(2021), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(1949), Year.of(2022))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2023), Year.of(2035))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @Test
  void ensuresElectionAndTemporaryHolidays() {
    assertFor(REPUBLIC_OF_KOREA)
      .hasFixedHoliday("ELECTION_DAY", MARCH, 9)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("ELECTION_DAY", JUNE, 1)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("ELECTION_DAY", JUNE, 3)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("ELECTION_DAY", JUNE, 3)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("TEMPORARY_PUBLIC_HOLIDAY", OCTOBER, 1)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("TEMPORARY_PUBLIC_HOLIDAY", JANUARY, 27)
        .validBetween(Year.of(2025), Year.of(2025))
      .check();
  }

  @Test
  void ensuresLunarHolidays() {
    assertFor(REPUBLIC_OF_KOREA)
      // 2014 - the day preceding Chuseok fell on a Sunday; the substitute is an
      // additional entry, the statutory date remains a holiday
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", SEPTEMBER, 7)
        .validBetween(Year.of(2014), Year.of(2014))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 8)
        .validBetween(Year.of(2014), Year.of(2014))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 10) // substitute
        .validBetween(Year.of(2014), Year.of(2014))
      .and()
      // 2016 - Seollal-preceding day fell on a Sunday
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 8)
        .validBetween(Year.of(2016), Year.of(2016))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 10) // substitute
        .validBetween(Year.of(2016), Year.of(2016))
      .and()
      // 2017 - Chuseok-preceding day coincided with National Foundation Day
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", OCTOBER, 3)
        .validBetween(Year.of(2017), Year.of(2017))
      .and()
      .hasFixedHoliday("CHUSEOK", OCTOBER, 6) // substitute for the overlap
        .validBetween(Year.of(2017), Year.of(2017))
      .and()
      // 2023 - Seollal fell on a Sunday; statutory date and substitute both present
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", JANUARY, 22)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", JANUARY, 24) // substitute
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // 2023 - Buddha's Birthday, first substitute after the May 2023 rule extension
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 27)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 29) // substitute
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // 2024
      .hasFixedHoliday("DAY_FOLLOWING_LUNAR_NEW_YEAR", FEBRUARY, 11)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 12) // substitute
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      // 2025 - Buddha's Birthday coincided with Children's Day; the day preceding
      // Chuseok fell on a Sunday, substitute observed on Wednesday 8 October
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 5)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 6) // substitute
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("DAY_PRECEDING_CHUSEOK", OCTOBER, 5)
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      .hasFixedHoliday("CHUSEOK", OCTOBER, 8) // substitute
        .validBetween(Year.of(2025), Year.of(2025))
      .and()
      // 2026 - Buddha's Birthday fell on a Sunday; Chuseok's Saturday correctly
      // yields no substitute
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 24)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 25) // substitute
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_CHUSEOK", SEPTEMBER, 26)
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      // 2027 - Seollal fell on a Sunday
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 7)
        .validBetween(Year.of(2027), Year.of(2027))
      .and()
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 9) // substitute
        .validBetween(Year.of(2027), Year.of(2027))
      .and()
      // 2028 - Chuseok coincided with National Foundation Day
      .hasFixedHoliday("CHUSEOK", OCTOBER, 3)
        .validBetween(Year.of(2028), Year.of(2028))
      .and()
      .hasFixedHoliday("CHUSEOK", OCTOBER, 5) // substitute for the overlap
        .validBetween(Year.of(2028), Year.of(2028))
      .and()
      // 2029 - both Buddha's Birthday and the day following Chuseok fell on Sundays
      .hasFixedHoliday("BIRTHDAY_OF_THE_BUDDHA", MAY, 21) // substitute
        .validBetween(Year.of(2029), Year.of(2029))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 24) // substitute
        .validBetween(Year.of(2029), Year.of(2029))
      .and()
      // 2033 - the day preceding Seollal fell on a Sunday
      .hasFixedHoliday("LUNAR_NEW_YEARS_DAY", FEBRUARY, 2) // substitute
        .validBetween(Year.of(2033), Year.of(2033))
      .and()
      // 2035 - Chuseok fell on a Sunday (last covered year)
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 16)
        .validBetween(Year.of(2035), Year.of(2035))
      .and()
      .hasFixedHoliday("CHUSEOK", SEPTEMBER, 18) // substitute
        .validBetween(Year.of(2035), Year.of(2035))
      .check();
  }
}
