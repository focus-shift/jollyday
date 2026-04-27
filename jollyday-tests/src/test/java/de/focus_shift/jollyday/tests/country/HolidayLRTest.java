package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.LIBERIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayLRTest {

  @Test
  void ensuresHolidays() {
    assertFor(LIBERIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ARMED_FORCES_DAY", FEBRUARY, 11).and()
      .hasFixedHoliday("JOSEPH_JENKINS_ROBERTS_BIRTHDAY", MARCH, 15).and()
      .hasFixedHoliday("NATIONAL_UNIFICATION_DAY", MAY, 14).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 26).and()
      .hasFixedHoliday("FLAG_DAY", AUGUST, 24).and()
      .hasFixedHoliday("WILLIAM_TUBMAN_BIRTHDAY", NOVEMBER, 29).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}
