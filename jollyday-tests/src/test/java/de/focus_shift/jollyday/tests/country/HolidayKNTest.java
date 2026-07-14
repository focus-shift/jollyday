package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_KITTS_AND_NEVIS;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayKNTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_KITTS_AND_NEVIS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
      .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CARNIVAL_DAY", JANUARY, 2).and()
      .hasFixedHoliday("BUCKLYS_UPRISING_DAY", JANUARY, 28).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", SEPTEMBER, 16).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 19).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST).and()
      .hasFixedWeekdayHoliday("CULTURAMA_DAY", FIRST, TUESDAY, AUGUST)
      .check();
  }
}
