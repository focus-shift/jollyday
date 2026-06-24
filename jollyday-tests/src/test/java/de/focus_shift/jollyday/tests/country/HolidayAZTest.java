package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.AZERBAIJAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.NOVEMBER;

class HolidayAZTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(AZERBAIJAN)
      // New Year's Day (2 days)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      // Martyrs' Day - Black January 1990
      .hasFixedHoliday("MARTYRS_DAY", JANUARY, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      // Women's Day
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      // Nowruz (5 days)
      .hasFixedHoliday("NOWRUZ", MARCH, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 22).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 24).validBetween(YEAR_FROM, YEAR_TO).and()
      // Victory Day over Fascism
      .hasFixedHoliday("DAY_OF_VICTORY_OVER_FASCISM", MAY, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      // Independence Day
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      // National Salvation Day
      .hasFixedHoliday("NATIONAL_SALVATION_DAY", JUNE, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      // Azerbaijan Armed Forces Day
      .hasFixedHoliday("ARMED_FORCES_DAY", JUNE, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      // Victory Day (2020 Nagorno-Karabakh war)
      .hasFixedHoliday("VICTORY_DAY", NOVEMBER, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      // State Flag Day
      .hasFixedHoliday("STATE_FLAG_DAY", NOVEMBER, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      // International Solidarity Day of Azerbaijanis
      .hasFixedHoliday("INTERNATIONAL_SOLIDARITY_DAY", DECEMBER, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      // Eid al-Fitr (2 days)
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      // Eid al-Adha (2 days)
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
