package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ISLE_OF_MAN;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayIMTest {

  @Test
  void ensuresHolidays() {

    assertFor(ISLE_OF_MAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("TYNWALD", JULY, 5)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      // The 2020 Early May Bank Holiday was moved from the 4th to the 8th of May to mark VE Day's 75th anniversary
      .hasFixedHoliday("EARLY_MAY_BANK_HOLIDAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2024), Year.of(2024))
      .and()
      // The 2022 Spring Bank Holiday was moved from the 30th of May to the 2nd of June
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      // The 2015 and 2020 T.T. Bank Holidays were moved from the 5th of June to the 12th of June
      .hasFixedHoliday("TT_BANK_HOLIDAY", JUNE, 12)
        .validBetween(Year.of(2015), Year.of(2015))
      .and()
      .hasFixedHoliday("TT_BANK_HOLIDAY", JUNE, 12)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      // The 2021 T.T. Bank Holiday was moved from the 11th of June to the 27th of August
      .hasFixedHoliday("TT_BANK_HOLIDAY", AUGUST, 27)
        .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedHoliday("STATE_FUNERAL_QUEEN_ELIZABETH_II", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validBetween(Year.of(1900), Year.of(2019))
      .and()
      // 2020 it was moved to the second Friday of May (see the Fixed holiday above); resumes from 2021
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validFrom(Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("LATE_MAY_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2021))
      .and()
      // 2022 it was moved to the first Thursday of June (see the Fixed holiday above); resumes from 2023
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      // the Friday of race week (first week of June), 11 days after the start of Isle of Man TT on Spring Bank Holiday
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validBetween(Year.of(1907), Year.of(2014))
      .and()
      // 2015 it was moved to the second Friday in June as TT Senior Race was moved one week later
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validBetween(Year.of(2016), Year.of(2019))
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validFrom(Year.of(2022))
      .check();
  }
}
