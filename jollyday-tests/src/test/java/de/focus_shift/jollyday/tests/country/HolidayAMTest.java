package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARMENIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayAMTest {

  @Test
  void ensuresHolidays() {

    assertFor(ARMENIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 5).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 6).and()
      .hasFixedHoliday("ARMY_DAY", JANUARY, 28).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("ARMENIAN_GENOCIDE_REMEMBRANCE_DAY", APRIL, 24).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_AND_PEACE_DAY", MAY, 9).and()
      .hasFixedHoliday("REPUBLIC_DAY", MAY, 28).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JULY, 5).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 21).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER")
      .check();
  }
}
