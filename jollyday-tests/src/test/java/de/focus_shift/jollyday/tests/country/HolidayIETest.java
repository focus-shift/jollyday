package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.IRELAND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;

class HolidayIETest {

  @Test
  void ensuresHolidays() {

    assertFor(IRELAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ST_PATRICK", MARCH, 17).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasFixedHoliday("ST_BRIGID", FEBRUARY, 1)
        .between(Year.of(2023), Year.of(2500))
        .canBeShiftedFrom(TUESDAY, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, MONDAY)
        .canBeShiftedFrom(THURSDAY, MONDAY)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }

}
