package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MACEDONIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayMKTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(MACEDONIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CYRUS_METHODIUS", MAY, 24).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REPUBLIC_DAY", AUGUST, 2).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 8).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REVOLUTION", OCTOBER, 11).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", OCTOBER, 23).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SAINT_CLEMENT", DECEMBER, 8).between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
