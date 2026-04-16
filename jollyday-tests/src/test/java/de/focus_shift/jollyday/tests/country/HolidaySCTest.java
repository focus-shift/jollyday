package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SEYCHELLES;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidaySCTest {

  @Test
  void ensuresHolidays() {
    assertFor(SEYCHELLES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR_HOLIDAY", JANUARY, 2).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("ABOLITION_OF_SLAVERY", FEBRUARY, 1).validFrom(Year.of(2026)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JUNE, 18).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 29).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }

}
