package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.REUNION;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayRETest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(REUNION)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ABOLITION_OF_SLAVERY", DECEMBER, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO).and()
      // Whit Monday was not a day off from 2004 to 2007 (Journée de solidarité)
      .hasChristianHoliday("WHIT_MONDAY")
        .validBetween(YEAR_FROM, Year.of(2003))
        .notValidBetween(Year.of(2004), Year.of(2007))
        .validBetween(Year.of(2008), YEAR_TO)
      .check();
  }
}
