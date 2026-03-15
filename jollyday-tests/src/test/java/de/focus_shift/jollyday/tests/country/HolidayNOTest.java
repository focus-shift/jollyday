package de.focus_shift.jollyday.tests.country;

import static de.focus_shift.jollyday.core.HolidayCalendar.NORWAY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;

import org.junit.jupiter.api.Test;

class HolidayNOTest {

  @Test
  void ensuresHolidays() {
    assertFor(NORWAY)
        .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .and()
        .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .and()
        .hasFixedHoliday("CONSTITUTION_DAY", MAY, 17)
        .and()
        .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .and()
        .hasFixedHoliday("STEPHENS", DECEMBER, 26)
        .and()
        .hasChristianHoliday("EASTER")
        .and()
        .hasChristianHoliday("MAUNDY_THURSDAY")
        .and()
        .hasChristianHoliday("GOOD_FRIDAY")
        .and()
        .hasChristianHoliday("EASTER_MONDAY")
        .and()
        .hasChristianHoliday("ASCENSION_DAY")
        .and()
        .hasChristianHoliday("WHIT_MONDAY")
        .and()
        .hasChristianHoliday("PENTECOST")
        .check();
  }
}
