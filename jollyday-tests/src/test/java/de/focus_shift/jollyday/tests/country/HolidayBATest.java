package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BOSNIA_HERZIGOWINA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.SEPTEMBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;

class HolidayBATest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(BOSNIA_HERZIGOWINA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("MAY_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_GEORGE", MAY, 6).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_VITUS", JUNE, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_PETER", JULY, 12).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_ELIJAH", AUGUST, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIVITY_MARY", SEPTEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIVITY_MARY", SEPTEMBER, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_DEMETRIUS", NOVEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()

      /* Republika Srpska */
      .hasFixedHoliday("REPUBLIC_DAY", JANUARY, 9)
        .inSubdivision("srp")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .inSubdivision("srp")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("DAYTON", NOVEMBER, 21)
        .inSubdivision("srp")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()

      /* Federation of Bosnia and Herzegovina */
      .hasFixedHoliday("INDEPENDENCE_DAY", MARCH, 1)
        .inSubdivision("bih")
        .validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", NOVEMBER, 25)
        .inSubdivision("bih")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}

