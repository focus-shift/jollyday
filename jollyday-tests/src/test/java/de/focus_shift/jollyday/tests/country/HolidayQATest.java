package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.QATAR;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.SEPTEMBER;

class HolidayQATest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(QATAR)
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 3)
        .validBetween(YEAR_FROM, Year.of(2006))
      .and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 18)
        .validBetween(Year.of(2007), YEAR_TO)
        .canBeMovedFrom(SATURDAY, SUNDAY)
      .and()
      .hasFixedWeekdayHoliday("SPORTS_DAY", SECOND, TUESDAY, FEBRUARY)
        .validBetween(Year.of(2012), YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_3").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ARAFAAT").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_3").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
