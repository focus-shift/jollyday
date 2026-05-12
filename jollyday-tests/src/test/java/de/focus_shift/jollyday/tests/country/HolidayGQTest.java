package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.EQUATORIAL_GUINEA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayGQTest {

  @Test
  void ensuresHolidays() {
    assertFor(EQUATORIAL_GUINEA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PRESIDENTS_DAY", JUNE, 5).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("FREEDOM_DAY", AUGUST, 3).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CONSTITUTION_DAY", AUGUST, 15).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 12).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
