package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAO_TOME_AND_PRINCIPE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidaySTTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAO_TOME_AND_PRINCIPE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("MARTYRS_DAY", FEBRUARY, 3).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 12).and()
      .hasFixedHoliday("ARMED_FORCES_DAY", SEPTEMBER, 6).and()
      .hasFixedHoliday("AGRICULTURAL_REFORM_DAY", SEPTEMBER, 30).and()
      .hasFixedHoliday("SAO_TOME_DAY", DECEMBER, 21).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}
