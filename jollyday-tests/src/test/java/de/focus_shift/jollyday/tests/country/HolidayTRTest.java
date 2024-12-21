package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TURKEY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayTRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(TURKEY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_CHILDRENS_DAY", APRIL, 23)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_COMMEMORATION_OF_ATATURK", MAY, 19)
        .between(Year.of(2004), YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_DEMOCRATIC_UNITY_DAY", JULY, 15)
        .between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("TURKEY_VICTORY_DAY", AUGUST, 30)
        .between(Year.of(2003), YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_REPUBLIC_DAY", OCTOBER, 29)
        .between(Year.of(2003), YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
