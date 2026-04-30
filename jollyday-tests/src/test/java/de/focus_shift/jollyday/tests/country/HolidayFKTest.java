package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.FALKLAND_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayFKTest {

  @Test
  void ensuresHolidays() {
    assertFor(FALKLAND_ISLANDS)
      // New Year's Day - moves if on weekend
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Good Friday
      .hasChristianHoliday("GOOD_FRIDAY")
      .and()
      // Liberation Day - commemorates the 1982 end of the Falklands War - moves if on weekend
      .hasFixedHoliday("LIBERATION", JUNE, 14)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // King's Birthday - 14 November - moves if on weekend
      .hasFixedHoliday("KINGS_BIRTHDAY", NOVEMBER, 14)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Battle Day - commemorates the 1845 Battle of Camp - moves if on weekend
      .hasFixedHoliday("BATTLE_DAY", DECEMBER, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Christmas Day - moves if on weekend
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Boxing Day - moves if on weekend
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Christmas Holiday - moves if on weekend
      .hasFixedHoliday("CHRISTMAS_HOLIDAY", DECEMBER, 27)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Government Holiday - moves if on weekend
      .hasFixedHoliday("GOVERNMENT_HOLIDAY", DECEMBER, 30)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Queen Elizabeth II State Funeral - one-off public holiday (2022 only)
      .hasFixedHoliday("FUNERAL_QUEEN_ELIZABETH_II", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .check();
  }
}
