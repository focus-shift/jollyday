package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ZAMBIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayZMTest {
  @Test
  void ensuresHolidays() {
    assertFor(ZAMBIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("YOUTH", MARCH, 12).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("KENNETH_KAUNDA_BIRTHDAY", APRIL, 28).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("AFRICAN_FREEDOM_DAY", MAY, 25).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_PRAYER", OCTOBER, 18).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 24).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
