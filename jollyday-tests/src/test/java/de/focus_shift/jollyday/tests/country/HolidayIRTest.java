package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.IRAN;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;

class HolidayIRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(IRAN)
      // Bahman 22: Anniversary of the victory of the Islamic Revolution (1979)
      .hasFixedHoliday("VICTORY_OF_ISLAMIC_REVOLUTION", FEBRUARY, 11).validBetween(YEAR_FROM, YEAR_TO).and()
      // Esfand 29: Nationalization of the Iranian oil industry (1951)
      .hasFixedHoliday("OIL_INDUSTRY_NATIONALIZATION_DAY", MARCH, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      // Farvardin 1-4: Nowruz
      .hasFixedHoliday("NOWRUZ", MARCH, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 22).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 24).validBetween(YEAR_FROM, YEAR_TO).and()
      // Farvardin 12: Islamic Republic Day
      .hasFixedHoliday("ISLAMIC_REPUBLIC_DAY", APRIL, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      // Farvardin 13: Sizdah Bedar (Nature Day)
      .hasFixedHoliday("SIZDAH_BEDAR", APRIL, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      // Khordad 14: Demise of Imam Khomeini
      .hasFixedHoliday("DEMISE_OF_IMAM_KHOMEINI", JUNE, 4).validBetween(YEAR_FROM, YEAR_TO).and()
      // Khordad 15: Uprising of 15 Khordad
      .hasFixedHoliday("FIFTEENTH_OF_KHORDAD_UPRISING", JUNE, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      // Islamic (lunar Hijri) holidays
      .hasIslamicHoliday("TASUA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ARBAEEN").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_MUHAMMAD_AND_HASAN_MOJTABA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_IMAM_REZA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_IMAM_HASAN_ASKARI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("BIRTH_OF_MUHAMMAD_AND_IMAM_SADIQ").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_FATIMA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("BIRTH_OF_IMAM_ALI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("EID_E_MABATH", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("BIRTH_OF_IMAM_MAHDI", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_IMAM_ALI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("DEATH_OF_IMAM_SADIQ").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_GHADIR").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
