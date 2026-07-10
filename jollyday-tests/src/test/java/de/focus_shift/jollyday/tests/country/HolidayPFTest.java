package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.FRENCH_POLYNESIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayPFTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(FRENCH_POLYNESIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("GOSPEL_DAY", MARCH, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      // Fête de l'autonomie: instituted in 1985, repealed from 2026 by Loi du Pays n° 2025-12
      .hasFixedHoliday("AUTONOMY_DAY", JUNE, 29)
        .notValidBetween(YEAR_FROM, Year.of(1984))
        .validBetween(Year.of(1985), Year.of(2025))
        .notValidBetween(Year.of(2026), YEAR_TO)
      .and()
      .hasFixedHoliday("NATIONAL_DAY", JULY, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARMISTICE", NOVEMBER, 11).validBetween(YEAR_FROM, YEAR_TO).and()
      // Matari'i: replaces AUTONOMY_DAY as jour férié from 2026 by Loi du Pays n° 2025-12
      .hasFixedHoliday("MATARII", NOVEMBER, 20)
        .notValidBetween(YEAR_FROM, Year.of(2025))
        .validBetween(Year.of(2026), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
