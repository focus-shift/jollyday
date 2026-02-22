package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.INDIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;

class HolidayINTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(INDIA)
      .hasFixedHoliday("REPUBLIC_DAY", JANUARY, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("GHANDIS_BIRTHDAY", OCTOBER, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
