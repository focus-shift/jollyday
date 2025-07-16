package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.CHILE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayCLTest {

  @Test
  void ensuresHolidays() {
    assertFor(CHILE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ELECTION_DAY", JANUARY, 17)
        .between(Year.of(2010), Year.of(2010)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NAVY_DAY", MAY, 21).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 28).and()
      .hasFixedHoliday("VIRGIN_CARMEN", JULY, 16)
        .from(Year.of(2007))
      .and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("NATIONAL_DAY", SEPTEMBER, 18).and()
      .hasFixedHoliday("ARMY_DAY", SEPTEMBER, 19).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 11).and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .from(Year.of(2008))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
        .to(Year.of(2006))
      .check();
  }
}

