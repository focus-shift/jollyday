package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.NICARAGUA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayNITest {

  @Test
  void ensuresHolidays() {
    assertFor(NICARAGUA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("BIRTH_OF_RUBEN_DARIO", JANUARY, 18)
        .notValidBetween(Year.of(1900), Year.of(2026))
        .validFrom(Year.of(2027))
      .and()
      .hasFixedHoliday("AIR_FORCE_DAY", FEBRUARY, 1, OBSERVANCE).and()
      .hasFixedHoliday("PEACE_AND_RECONCILIATION", FEBRUARY, 2)
        .notValidBetween(Year.of(1900), Year.of(2025))
        .validFrom(Year.of(2026))
      .and()
      .hasFixedHoliday("DEATH_OF_SANDINO", FEBRUARY, 21)
        .notValidBetween(Year.of(1900), Year.of(2025))
        .validFrom(Year.of(2026))
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ARMY_DAY", MAY, 27, OBSERVANCE).and()
      .hasFixedHoliday("MOTHERS_DAY", MAY, 30)
        .notValidBetween(Year.of(1900), Year.of(2021))
        .validFrom(Year.of(2022))
      .and()
      .hasFixedHoliday("LIBERATION", JULY, 19).and()
      .hasFixedHoliday("BATTLE_JACINTO", SEPTEMBER, 14).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 15).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12, OBSERVANCE).and()
      .hasFixedHoliday("DEATH_OF_CARLOS_FONSECA", NOVEMBER, 8)
        .notValidBetween(Year.of(1900), Year.of(2025))
        .validFrom(Year.of(2026))
      .and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE).and()
      .hasFixedHoliday("SANTO_DOMINGO", AUGUST, 1)
        .inSubdivision("mn")
      .and()
      .hasFixedHoliday("SANTO_DOMINGO", AUGUST, 10)
        .inSubdivision("mn")
      .and()
      .hasChristianHoliday("EASTER", OBSERVANCE).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }
}
