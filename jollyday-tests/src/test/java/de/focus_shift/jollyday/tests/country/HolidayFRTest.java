package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.FRANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayFRTest {

  @Test
  void ensuresHolidays() {
    assertFor(FRANCE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8).and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 14).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
        .between(Year.of(1900), Year.of(2003))
        .notBetween(Year.of(2004), Year.of(2007))
        .between(Year.of(2008), Year.of(2500))
      .and()

      .hasFixedHoliday("ABOLITION_OF_SLAVERY", DECEMBER, 26)
        .inSubdivision("57")
      .and()

      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26)
        .inSubdivision("67")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .inSubdivision("67")
      .and()

      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26)
        .inSubdivision("68")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .inSubdivision("68")

      .check();
  }
}
