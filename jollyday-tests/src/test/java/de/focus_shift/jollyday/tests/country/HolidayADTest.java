package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANDORRA;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertChristian;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertFixed;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayADTest {

  @Test
  void ensuresHolidays() {
    assertFixed(ANDORRA, JANUARY, 1, "NEW_YEAR");
    assertFixed(ANDORRA, JANUARY, 6, "EPIPHANY");
    assertFixed(ANDORRA, MARCH, 14, "CONSTITUTION_DAY");
    assertFixed(ANDORRA, MAY, 1, "LABOUR_DAY");
    assertFixed(ANDORRA, AUGUST, 15, "ASSUMPTION_DAY");
    assertFixed(ANDORRA, SEPTEMBER, 8, "NATIONAL_DAY");
    assertFixed(ANDORRA, NOVEMBER, 1, "ALL_SAINTS");
    assertFixed(ANDORRA, DECEMBER, 8, "IMMACULATE_CONCEPTION");
    assertFixed(ANDORRA, DECEMBER, 25, "CHRISTMAS");
    assertFixed(ANDORRA, DECEMBER, 26, "STEPHENS");
    assertChristian(ANDORRA, "CARNIVAL");
    assertChristian(ANDORRA, "GOOD_FRIDAY");
    assertChristian(ANDORRA, "EASTER_MONDAY");
    assertChristian(ANDORRA, "WHIT_MONDAY");
  }
}
