package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.MOZAMBIQUE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayMZTest {

  @Test
  void ensuresHolidays() {
    assertFor(MOZAMBIQUE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("HEROES", FEBRUARY, 3).and()
      .hasFixedHoliday("MOZAMBIQUE_WOMENS_DAY", APRIL, 7).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 25).and()
      .hasFixedHoliday("VICTORY_DAY", SEPTEMBER, 7).and()
      .hasFixedHoliday("ARMED_FORCES_DAY", SEPTEMBER, 25).and()
      .hasFixedHoliday("PEACE_AND_RECONCILIATION", OCTOBER, 4).and()
      .hasFixedHoliday("FAMILY_DAY", DECEMBER, 25)
      .check();
  }
}
