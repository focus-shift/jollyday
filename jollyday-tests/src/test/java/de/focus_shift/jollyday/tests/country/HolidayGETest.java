package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.GEORGIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayGETest {

  @Test
  void ensuresHolidays() {
    assertFor(GEORGIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 19).and()
      .hasFixedHoliday("MOTHERS_DAY", MARCH, 3).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).and()
      .hasFixedHoliday("NATIONAL_UNITY_DAY", APRIL, 9).and()
      .hasFixedHoliday("DAY_OF_VICTORY_OVER_FASCISM", MAY, 9).and()
      .hasFixedHoliday("ST_ANDREW_FIRST_CALLED_DAY", MAY, 12).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 26).and()
      .hasFixedHoliday("ST_MARY", AUGUST, 28).and()
      .hasFixedHoliday("DAY_OF_SVETITSKHOVELI_CATHEDRA", OCTOBER, 14).and()
      .hasFixedHoliday("ST_GEORGE", NOVEMBER, 23).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
