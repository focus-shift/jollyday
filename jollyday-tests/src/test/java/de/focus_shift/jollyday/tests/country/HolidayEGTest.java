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
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_25_JAN_REVOLUTION", JANUARY, 25)
        .between(Year.of(2012), YEAR_TO)
        .notBetween(Year.of(1900), Year.of(2011))
      .and()
      .hasFixedHoliday("EGYPT_SINAI_LIBERATION", APRIL, 25)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_30_JUNE_REVOLUTION", JUNE, 30)
        .between(Year.of(2015), YEAR_TO)
        .notBetween(Year.of(1900), Year.of(2014))
      .and()
      .hasFixedHoliday("EGYPT_23_JULY_REVOLUTION", JULY, 23)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("EGYPT_ARMED_FORCES_DAY", OCTOBER, 6)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_2")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_3")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ARAFAAT")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_2")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_3")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("NEWYEAR")
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("MAWLID_AN_NABI")
        .between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
