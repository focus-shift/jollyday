package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;


import static de.focus_shift.jollyday.core.HolidayCalendar.SWITZERLAND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.NOVEMBER;


class HolidayCHTest {

  @Test
  void ensuresHolidays() {
    assertFor(SWITZERLAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", AUGUST, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("EASTER").and()

      /* Aargau */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("ag").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("ag").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("ag").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ag").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("ag").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ag").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ag").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ag").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ag").and()

      /* Vaud */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("vd").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("vd").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("vd").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("vd").and()

      /* Valais */
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).inSubdivision("vs").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("vs").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("vs").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("vs").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("vs")

      .check();
  }
}
