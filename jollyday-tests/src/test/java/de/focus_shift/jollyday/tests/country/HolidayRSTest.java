package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SERBIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayRSTest {

  @Test
  void ensuresAllSerbianHolidays() {
    assertFor(SERBIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).and()
      .hasFixedHoliday("STATEHOOD", FEBRUARY, 15)
        .canBeShiftedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("STATEHOOD", FEBRUARY, 16)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeShiftedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 2)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
