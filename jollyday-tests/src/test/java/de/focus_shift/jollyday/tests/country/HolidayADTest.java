package de.focus_shift.jollyday.tests.country;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANDORRA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

import org.junit.jupiter.api.Test;

class HolidayADTest {

  @Test
  void ensuresHolidays() {
    assertFor(ANDORRA)
        .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .and()
        .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .and()
        .hasFixedHoliday("CONSTITUTION_DAY", MARCH, 14)
        .and()
        .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .and()
        .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15)
        .and()
        .hasFixedHoliday("NATIONAL_DAY", SEPTEMBER, 8)
        .and()
        .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .and()
        .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8)
        .and()
        .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .and()
        .hasFixedHoliday("STEPHENS", DECEMBER, 26)
        .and()
        .hasChristianHoliday("CARNIVAL")
        .and()
        .hasChristianHoliday("GOOD_FRIDAY")
        .and()
        .hasChristianHoliday("EASTER_MONDAY")
        .and()
        .hasChristianHoliday("WHIT_MONDAY")
        .check();
  }
}
