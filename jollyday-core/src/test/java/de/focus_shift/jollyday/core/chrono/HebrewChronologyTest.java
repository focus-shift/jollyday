package de.focus_shift.jollyday.core.chrono;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

class HebrewChronologyTest {

  private final HebrewChronology chronology = HebrewChronology.INSTANCE;

  // Cross-validated against published Hebrew/Gregorian date conversions
  // (e.g. Rosh Hashanah start dates for several consecutive Hebrew years).
  @ParameterizedTest
  @CsvSource({
    "5782, 7, 1, 2021-09-07",
    "5783, 7, 1, 2022-09-26",
    "5784, 7, 1, 2023-09-16",
    "5785, 7, 1, 2024-10-03",
    "5786, 7, 1, 2025-09-23",
    "5786, 1, 15, 2026-04-02", // Pesach 5786
    "5783, 7, 10, 2022-10-05", // Yom Kippur 5783
  })
  void ensureHebrewDateConvertsToExpectedGregorianDate(final int year, final int month, final int day, final LocalDate expected) {
    final ChronoLocalDate hebrewDate = chronology.date(year, month, day);
    assertThat(LocalDate.from(hebrewDate)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
    "5784, true",  // leap year (13 months)
    "5785, false",
    "5786, false",
    "5787, true",
  })
  void ensureLeapYearIsCorrect(final int year, final boolean expectedLeap) {
    assertThat(chronology.isLeapYear(year)).isEqualTo(expectedLeap);
  }

  @Test
  void ensureRoundTripThroughEpochDayIsStable() {
    final ChronoLocalDate original = chronology.date(5786, 7, 1);
    final long epochDay = original.getLong(ChronoField.EPOCH_DAY);
    final ChronoLocalDate reconstructed = chronology.dateEpochDay(epochDay);
    assertThat(reconstructed).isEqualTo(original);
  }

  @Test
  void ensureDateFromGregorianLocalDateRoundTrips() {
    final LocalDate gregorian = LocalDate.of(2025, 9, 23);
    final ChronoLocalDate hebrewDate = chronology.date(gregorian);
    assertThat(hebrewDate.get(ChronoField.YEAR)).isEqualTo(5786);
    assertThat(hebrewDate.get(ChronoField.MONTH_OF_YEAR)).isEqualTo(7);
    assertThat(hebrewDate.get(ChronoField.DAY_OF_MONTH)).isEqualTo(1);
    assertThat(LocalDate.from(hebrewDate)).isEqualTo(gregorian);
  }

  @Test
  void ensurePlusDaysMatchesGregorianArithmetic() {
    final ChronoLocalDate hebrewDate = chronology.date(2025 + 3760, 7, 1);
    final ChronoLocalDate plusOneDay = hebrewDate.plus(1, ChronoUnit.DAYS);
    assertThat(LocalDate.from(plusOneDay)).isEqualTo(LocalDate.from(hebrewDate).plusDays(1));
  }

  @Test
  void ensureMonthLengthAccountsForLeapYear() {
    assertThat(chronology.date(5784, 12, 1).lengthOfMonth()).isEqualTo(30); // Adar I in a leap year has 30 days
    assertThat(chronology.date(5784, 13, 1).lengthOfMonth()).isEqualTo(29); // Adar II always has 29 days
    assertThat(chronology.date(5785, 12, 1).lengthOfMonth()).isEqualTo(29); // Adar in a non-leap year has 29 days
  }
}
