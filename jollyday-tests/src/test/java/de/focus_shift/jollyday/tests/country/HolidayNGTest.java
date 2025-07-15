package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NIGERIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.DECEMBER;

class HolidayNGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(NIGERIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHILDRENS_DAY", MAY, 27).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", MAY, 29).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
