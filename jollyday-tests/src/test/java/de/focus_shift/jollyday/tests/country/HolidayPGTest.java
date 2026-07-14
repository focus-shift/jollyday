package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PAPUA_NEW_GUINEA;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;

class HolidayPGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(PAPUA_NEW_GUINEA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("SOMARE_REMEMBRANCE_DAY", FEBRUARY, 26).validFrom(Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("REMEMBRANCE", JULY, 23).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("REPENTANCE_DAY", AUGUST, 26).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 16).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_SATURDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE).validTo(Year.of(2022)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validFrom(Year.of(2023))
      .check();
  }
}
