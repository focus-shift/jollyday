package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANGOLA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayAOTest {

  @Test
  void ensuresHolidays() {
    assertFor(ANGOLA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LIBERATION", FEBRUARY, 4).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("DAY_OF_THE_LIBERATION_OF_SOUTHERN_AFRICA", MARCH, 23).and()
      .hasFixedHoliday("PEACE", APRIL, 4).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", SEPTEMBER, 17).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 11).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
