package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.AUSTRIA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayATTest {

  @Test
  void ensuresHolidays() {

    assertFor(AUSTRIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("NATIONAL_DAY", OCTOBER, 26).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI").and()
      .hasFixedHoliday("MARTINS_DAY", NOVEMBER, 11).inSubdivision("1").and()
      .hasFixedHoliday("JOSEFS_DAY", MARCH, 19).inSubdivision("2").and()
      .hasFixedHoliday("PLEBISCITE", OCTOBER, 10).inSubdivision("2").and()
      .hasFixedHoliday("LEOPOLD", NOVEMBER, 15).inSubdivision("3").and()
      .hasFixedHoliday("FLORIAN", MAY, 4).inSubdivision("4").and()
      .hasFixedHoliday("RUPERT", SEPTEMBER, 24).inSubdivision("5").and()
      .hasFixedHoliday("JOSEFS_DAY", MARCH, 19).inSubdivision("6").and()
      .hasFixedHoliday("JOSEFS_DAY", MARCH, 19).inSubdivision("7").and()
      .hasFixedHoliday("JOSEFS_DAY", MARCH, 19).inSubdivision("8").and()
      .hasFixedHoliday("LEOPOLD", NOVEMBER, 15).inSubdivision("9")
      .check();
  }
}
