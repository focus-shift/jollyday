package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Relation.CLOSEST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidaySHTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, TUESDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validTo(Year.of(2022))
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("KINGS_BIRTHDAY", FIRST, FRIDAY, CLOSEST, MonthDay.of(NOVEMBER, 14))
        .validFrom(Year.of(2023))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()

      .hasFixedHoliday("ST_HELENA_DAY", MAY, 21)
        .inSubdivision("hl")
      .and()
      .hasFixedWeekdayHoliday("AUGUST_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .inSubdivision("hl")
      .and()

      .hasFixedWeekdayHoliday("AUGUST_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .inSubdivision("ac")
      .and()
      .hasChristianHoliday("ASCENSION_DAY")
        .inSubdivision("ac")
      .and()

      .hasFixedHoliday("ANNIVERSARY_DAY", AUGUST, 14)
        .inSubdivision("ta")
      .and()
      .hasChristianHoliday("ASCENSION_DAY")
        .inSubdivision("ta")

      .check();
  }
}
