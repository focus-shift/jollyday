package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MALDIVES;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayMVTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(MALDIVES)
      // Not in Employment Act s.97, but universally observed as a government/bank closure
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Added to Employment Act s.97 by the 9th Amendment (2026); observed in practice earlier
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 26).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY_2", JULY, 27).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Commemorates the failure of the 3 November 1988 coup attempt
      .hasFixedHoliday("VICTORY_DAY", NOVEMBER, 3).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Commemorates the founding of the Second Republic on 11 November 1968
      .hasFixedHoliday("REPUBLIC_DAY", NOVEMBER, 11).validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Not in Employment Act s.97, but observed as a government/bank closure in practice
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // National Day (Qaumee Dhuvas), 1 Rabi al-Awwal
      .hasIslamicHoliday("QAUMEE_DHUVAS").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Mawlid, 12 Rabi al-Awwal
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // The Day Maldives Embraced Islam, 1 Rabi al-Thani
      .hasIslamicHoliday("EMBRACING_OF_ISLAM_DAY").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // First Day of Ramadan
      .hasIslamicHoliday("RAMADAN").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_3").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Hajj Day / Day of Arafat
      .hasIslamicHoliday("ARAFAAT").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_3").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
