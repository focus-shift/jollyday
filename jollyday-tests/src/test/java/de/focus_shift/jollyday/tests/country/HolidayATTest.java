package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.AUSTRIA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertChristian;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertFixed;
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
    assertFixed(AUSTRIA, JANUARY, 1, "NEW_YEAR");
    assertFixed(AUSTRIA, JANUARY, 6, "EPIPHANY");
    assertFixed(AUSTRIA, MAY, 1, "LABOUR_DAY");
    assertFixed(AUSTRIA, AUGUST, 15, "ASSUMPTION_DAY");
    assertFixed(AUSTRIA, OCTOBER, 26, "NATIONAL_DAY");
    assertFixed(AUSTRIA, NOVEMBER, 1, "ALL_SAINTS");
    assertFixed(AUSTRIA, DECEMBER, 8, "IMMACULATE_CONCEPTION");
    assertFixed(AUSTRIA, DECEMBER, 24, "CHRISTMAS_EVE", OBSERVANCE);
    assertFixed(AUSTRIA, DECEMBER, 25, "CHRISTMAS");
    assertFixed(AUSTRIA, DECEMBER, 26, "STEPHENS");
    assertFixed(AUSTRIA, DECEMBER, 31, "NEW_YEARS_EVE", OBSERVANCE);
    assertChristian(AUSTRIA, "EASTER");
    assertChristian(AUSTRIA, "EASTER_MONDAY");
    assertChristian(AUSTRIA, "ASCENSION_DAY");
    assertChristian(AUSTRIA, "WHIT_MONDAY");
    assertChristian(AUSTRIA, "CORPUS_CHRISTI");

    assertFixed(AUSTRIA, "1", NOVEMBER, 11, "MARTINS_DAY");
    assertFixed(AUSTRIA, "2", MARCH, 19, "JOSEFS_DAY");
    assertFixed(AUSTRIA, "2", OCTOBER, 10, "PLEBISCITE");
    assertFixed(AUSTRIA, "3", NOVEMBER, 15, "LEOPOLD");
    assertFixed(AUSTRIA, "4", MAY, 4, "FLORIAN");
    assertFixed(AUSTRIA, "5", SEPTEMBER, 24, "RUPERT");
    assertFixed(AUSTRIA, "6", MARCH, 19, "JOSEFS_DAY");
    assertFixed(AUSTRIA, "7", MARCH, 19, "JOSEFS_DAY");
    assertFixed(AUSTRIA, "8", MARCH, 19, "JOSEFS_DAY");
    assertFixed(AUSTRIA, "9", NOVEMBER, 15, "LEOPOLD");
  }
}
