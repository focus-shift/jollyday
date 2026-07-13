package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ICELAND;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;

class HolidayISTest extends AbstractCountryTestBase {

  private static final Year YEAR = Year.of(2010);
  private static final String ISO_CODE = "is";

  @Test
  void testManagerISStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void ensuresFixedWeekdayHolidays() {

    assertFor(ICELAND)
      .hasFixedWeekdayBetweenFixedHoliday("HUSBANDS_DAY", FRIDAY, MonthDay.of(JANUARY, 19), MonthDay.of(JANUARY, 25)).and()
      .hasFixedWeekdayRelativeToFixedHoliday("FIRST_DAY_SUMMER", FIRST, THURSDAY, AFTER, MonthDay.of(APRIL, 18))
      .check();
  }

}
