package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.LIECHTENSTEIN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayLITest {

  @Test
  void ensuresLiechtensteinHolidays() {
    assertFor(LIECHTENSTEIN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(of(1900), of(1966))
        .validBetween(of(1967), of(2500))
      .and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("CANDLEMAS", FEBRUARY, 2).and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).and()
      .hasFixedHoliday("MAY_DAY", MAY, 1).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("NATIVITY_LADY", SEPTEMBER, 8).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validBetween(of(1642), of(2500))
      .and()
      .hasChristianHoliday("WHIT_SUNDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
