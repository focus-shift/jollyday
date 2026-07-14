package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PHILIPPINES;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.APRIL;

class HolidayPHTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    // R.A. No. 9492 nominally moves several regular holidays to the "Monday nearest"
    // their statutory date, but the President has consistently overridden that default
    // every year observed (CY2024-CY2026) to keep them on their literal calendar date,
    // so all fixed-date holidays are asserted unmoved here.
    assertFor(PHILIPPINES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARAW_NG_KAGITINGAN", APRIL, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 12).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NINOY_AQUINO_DAY", AUGUST, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BONIFACIO_DAY", NOVEMBER, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("RIZAL_DAY", DECEMBER, 30).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedWeekdayHoliday("NATIONAL_HEROES_DAY", LAST, MONDAY, AUGUST).validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
