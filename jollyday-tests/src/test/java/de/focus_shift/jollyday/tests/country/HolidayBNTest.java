package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.BRUNEI;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayBNTest {

  // Java's HijrahChronology (Umm al-Qura) only covers roughly 1882-2174, so Islamic holidays
  // are asserted within this window (see HolidayKWTest).
  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(BRUNEI)
      // Fixed Gregorian holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", FEBRUARY, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARMED_FORCES_DAY", MAY, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SULTANS_BIRTHDAY", JULY, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      // Islamic (Hijri) holidays
      .hasIslamicHoliday("NEWYEAR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("LAILAT_AL_MIRAJ").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("RAMADAN").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NUZUL_AL_QURAN").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_3").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @ParameterizedTest
  @CsvSource({
    "2022, 2, 1",
    "2023, 1, 22",
    "2024, 2, 10",
    "2025, 1, 29",
    "2026, 2, 17",
    "2027, 2, 6",
  })
  void ensuresChineseNewYear(final int year, final int month, final int day) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(BRUNEI));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CHINESE_NEW_YEAR"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(year, month, day));
  }

  @ParameterizedTest
  @ValueSource(ints = {2022, 2023, 2024, 2025, 2026, 2027})
  void ensuresNuzulAlQuranIsSixteenDaysAfterStartOfRamadan(final int year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(BRUNEI));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    final LocalDate ramadan = dateOf(holidays, "islamic.RAMADAN");
    final LocalDate nuzul = dateOf(holidays, "islamic.NUZUL_AL_QURAN");

    // 1 Ramadan -> 17 Ramadan is exactly 16 days.
    assertThat(nuzul).isEqualTo(ramadan.plusDays(16));
  }

  private static LocalDate dateOf(final Set<Holiday> holidays, final String propertyKey) {
    return holidays.stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertyKey))
      .map(Holiday::getDate)
      .findFirst()
      .orElseThrow(() -> new AssertionError("Holiday not found: " + propertyKey));
  }
}
