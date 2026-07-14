package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.MONTSERRAT;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayMSTest {

  @Test
  void ensuresHolidays() {
    assertFor(MONTSERRAT)
      // Fixed date holidays with weekend substitution (Act No. 3 of 2017, Schedule paragraphs 3-5)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedHoliday("ST_PATRICK", MARCH, 17)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedHoliday("FESTIVAL_DAY", DECEMBER, 31)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).and()
      .hasFixedWeekdayHoliday("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", SECOND, WEDNESDAY, JULY).and()
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST)
      .check();
  }

  /**
   * Public Holidays (Amendment) Act, No. 3 of 2017, Schedule paragraphs 3-5:
   * if a public holiday falls on a Saturday or Sunday it is observed on the next
   * following Monday; where consecutive holidays fall on a weekend the following
   * Monday and Tuesday are observed.
   */
  @ParameterizedTest
  @CsvSource({
    // year, propertiesKey, expected observed date
    // New Year's Day (1 January)
    "2021, NEW_YEAR, 2021-01-01",       // Friday -> not moved
    "2022, NEW_YEAR, 2022-01-03",       // Saturday -> Monday
    "2023, NEW_YEAR, 2023-01-03",       // Sunday (Festival on Saturday takes Monday) -> Tuesday
    "2024, NEW_YEAR, 2024-01-02",       // Monday (Festival on Sunday takes Monday) -> Tuesday
    // Saint Patrick's Day (17 March)
    "2018, ST_PATRICK, 2018-03-19",     // Saturday -> Monday
    "2024, ST_PATRICK, 2024-03-18",     // Sunday -> Monday
    "2025, ST_PATRICK, 2025-03-17",     // Monday -> not moved
    // Christmas Day / Boxing Day collisions
    "2021, CHRISTMAS, 2021-12-27",      // Saturday -> Monday
    "2021, BOXING_DAY, 2021-12-28",     // Sunday -> Tuesday
    "2022, CHRISTMAS, 2022-12-26",      // Sunday -> Monday
    "2022, BOXING_DAY, 2022-12-27",     // Monday (Christmas takes Monday) -> Tuesday
    // Festival Day (31 December)
    "2022, FESTIVAL_DAY, 2023-01-02",   // Saturday -> Monday
    "2023, FESTIVAL_DAY, 2024-01-01",   // Sunday -> Monday
  })
  void ensuresWeekendSubstitution(final int year, final String propertiesKey, final String expectedDate) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MONTSERRAT));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.parse(expectedDate));
  }
}
