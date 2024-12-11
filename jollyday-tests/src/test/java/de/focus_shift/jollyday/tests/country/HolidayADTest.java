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
  void ensuresThatNewYearIsOnFirstJanuary() {
    assertFixed(ANDORRA, JANUARY, 1, "NEW_YEAR");
  }

  @Test
  void ensuresThatEpiphanyConfigured() {
    assertFixed(ANDORRA, JANUARY, 6, "EPIPHANY");
  }

  @Test
  void ensuresThatConstitutionDayIsConfigured() {
    assertFixed(ANDORRA, MARCH, 14, "CONSTITUTION_DAY");
  }

  @Test
  void ensuresThatLabourDayIsConfigured() {
    assertFixed(ANDORRA, MAY, 1, "LABOUR_DAY");
  }

  @Test
  void ensuresThatAssumptionDayIsConfigured() {
    assertFixed(ANDORRA, AUGUST, 15, "ASSUMPTION_DAY");
  }

  @Test
  void ensuresThatNationalDayIsConfigured() {
    assertFixed(ANDORRA, SEPTEMBER, 8, "NATIONAL_DAY");
  }

  @Test
  void ensuresThatAllSaintsIsConfigured() {
    assertFixed(ANDORRA, NOVEMBER, 1, "ALL_SAINTS");
  }

  @Test
  void ensuresThatImmaculateConceptionIsConfigured() {
    assertFixed(ANDORRA, DECEMBER, 8, "IMMACULATE_CONCEPTION");
  }

  @Test
  void ensuresThatChristmasIsConfigured() {
    assertFixed(ANDORRA, DECEMBER, 25, "CHRISTMAS");
    assertFixed(ANDORRA, DECEMBER, 26, "STEPHENS");
  }

  @Test
  void ensuresThatCarnivalIsConfigured() {
    assertChristian(ANDORRA, "CARNIVAL");
  }

  @Test
  void ensuresThatGoodFridayIsConfigured() {
    assertChristian(ANDORRA, "GOOD_FRIDAY");
  }

  @Test
  void ensuresThatEasterMondayIsConfigured() {
    assertChristian(ANDORRA, "EASTER_MONDAY");
  }

  @Test
  void ensuresThatWhitMondayIsConfigured() {
    assertChristian(ANDORRA, "WHIT_MONDAY");
  }
}
