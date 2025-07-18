package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MEXICO;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayMXTest {

  @Test
  void ensuresHolidays() {
    assertFor(MEXICO)
      .hasFixedHoliday("ARMY_DAY", FEBRUARY, 19, OBSERVANCE).and()
      .hasFixedHoliday("FLAG_DAY", FEBRUARY, 24, OBSERVANCE)
        .notBetween(Year.of(1900), Year.of(1936))
        .between(Year.of(1937), Year.of(2500))
      .and()
      .hasFixedHoliday("OIL_EXPROPRIATION_DAY", MARCH, 18, OBSERVANCE)
        .notBetween(Year.of(1900), Year.of(1937))
        .between(Year.of(1938), Year.of(2500))
      .and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12, OBSERVANCE).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeShiftedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 16)
        .canBeShiftedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeShiftedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .check();
  }
}
