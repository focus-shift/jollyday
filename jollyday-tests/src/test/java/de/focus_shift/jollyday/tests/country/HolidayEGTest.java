package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.EGYPT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;

class HolidayEGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(EGYPT)
      .hasFixedHoliday("EGYPT_COPTIC_CHRISTMAS", JANUARY, 7)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_25_JAN_REVOLUTION", JANUARY, 25)
        .validBetween(Year.of(2012), YEAR_TO)
        .notValidBetween(Year.of(1900), Year.of(2011))
      .and()
      .hasFixedHoliday("EGYPT_SINAI_LIBERATION", APRIL, 25)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_30_JUNE_REVOLUTION", JUNE, 30)
        .validBetween(Year.of(2015), YEAR_TO)
        .notValidBetween(Year.of(1900), Year.of(2014))
      .and()
      .hasFixedHoliday("EGYPT_23_JULY_REVOLUTION", JULY, 23)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_ARMED_FORCES_DAY", OCTOBER, 6)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_2")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_3")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ARAFAAT")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_2")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_3")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("NEWYEAR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("MAWLID_AN_NABI")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
