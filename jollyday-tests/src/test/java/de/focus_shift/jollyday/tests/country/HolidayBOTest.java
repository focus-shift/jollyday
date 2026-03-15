package de.focus_shift.jollyday.tests.country;

import static de.focus_shift.jollyday.core.HolidayCalendar.BOLIVIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

import org.junit.jupiter.api.Test;

class HolidayBOTest {

  @Test
  void ensuresHolidays() {
    assertFor(BOLIVIA)
        .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .and()
        .hasFixedHoliday("CANDELARIA", FEBRUARY, 2)
        .and()
        .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .and()
        .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 6)
        .and()
        .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .and()
        .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .and()
        .hasChristianHoliday("EASTER")
        .and()
        .hasChristianHoliday("GOOD_FRIDAY")
        .and()
        .hasChristianHoliday("CORPUS_CHRISTI")
        .check();
  }
}
