package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANTIGUA_AND_BARBUDA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayAGTest {

  @Test
  void ensuresHolidays() {
    assertFor(ANTIGUA_AND_BARBUDA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
      .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 1)
      .canBeMovedFrom(SATURDAY, MONDAY)
      .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", DECEMBER, 9).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .canBeMovedFrom(SATURDAY, MONDAY)
      .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("CARNIVAL_MONDAY", FIRST, MONDAY, AUGUST).and()
      .hasFixedWeekdayHoliday("CARNIVAL_TUESDAY", FIRST, TUESDAY, AUGUST).and()
      .hasFixedWeekdayHoliday("NATIONAL_DAY_OF_PRAYER", SECOND, THURSDAY, SEPTEMBER).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }
}
