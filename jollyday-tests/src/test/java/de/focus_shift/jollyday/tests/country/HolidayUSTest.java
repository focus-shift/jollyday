package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayUSTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "us";

  @ParameterizedTest
  @ValueSource(strings = {"2010", "2022"})
  void testManagerUSStructure(final Year year) {
    validateCalendarData(ISO_CODE, year);
  }

  @Test
  void ensuresFixedWeekdayHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER)
        .validFrom(Year.of(1863))
      .and()
      .hasFixedWeekdayHoliday("MEMORIAL_DAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(1968))
      .check();
  }
}
