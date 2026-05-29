package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.VANUATU;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayVUTest {

  @Test
  void ensuresHolidays() {
    assertFor(VANUATU)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("FATHER_WALTER_LINI_DAY", FEBRUARY, 21).and()
      .hasFixedHoliday("CUSTOM_CHIEFS_DAY", MARCH, 5).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("CHILDRENS_DAY", JULY, 24).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 30).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("CONSTITUTION_DAY", OCTOBER, 5).and()
      .hasFixedHoliday("NATIONAL_UNITY_DAY", NOVEMBER, 29).and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("FAMILY_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
      .check();
  }
}
