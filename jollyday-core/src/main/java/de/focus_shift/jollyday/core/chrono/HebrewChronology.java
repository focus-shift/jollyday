package de.focus_shift.jollyday.core.chrono;

import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.AbstractChronology;
import java.time.chrono.Era;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ValueRange;
import java.util.List;

/**
 * The Hebrew (Jewish lunisolar) calendar system.
 * <p>
 * This chronology defines the rules of the traditional Hebrew calendar, as used for
 * Jewish religious observance and as the basis for most public holidays in Israel.
 * <p>
 * The fields are defined as follows:
 * <ul>
 *   <li>era - There is a single era, 'AM' (Anno Mundi).
 *   <li>year-of-era / proleptic-year - Years increase uniformly from year 1.
 *   <li>month-of-year - There are 12 months in a regular year and 13 in a leap year,
 *   numbered in the traditional ecclesiastical order starting with Nisan (1) through
 *   Elul (6), then Tishrei (7) through Adar/Adar II (12/13). The calendar year itself
 *   begins with Tishrei (the civil new year, Rosh Hashanah) even though it is numbered 7.
 *   <li>day-of-month - Between 29 and 30 days, depending on the month and year type.
 *   <li>leap-year - 7 out of every 19 years (the Metonic cycle) are leap years, adding
 *   a second month of Adar.
 * </ul>
 * <p>
 * This class is immutable and thread-safe.
 */
public final class HebrewChronology extends AbstractChronology implements Serializable {

  /**
   * Singleton instance of the Hebrew chronology.
   */
  public static final HebrewChronology INSTANCE = new HebrewChronology();

  private static final long serialVersionUID = 1L;

  private HebrewChronology() {
  }

  private Object readResolve() {
    return INSTANCE;
  }

  @Override
  public @NonNull String getId() {
    return "Hebrew";
  }

  @Override
  public @NonNull String getCalendarType() {
    return "hebrew";
  }

  @Override
  public @NonNull HebrewDate date(final int prolepticYear, final int month, final int dayOfMonth) {
    return HebrewDate.of(prolepticYear, month, dayOfMonth);
  }

  @Override
  public @NonNull HebrewDate date(final @NonNull Era era, final int yearOfEra, final int month, final int dayOfMonth) {
    return date(prolepticYear(era, yearOfEra), month, dayOfMonth);
  }

  @Override
  public @NonNull HebrewDate dateYearDay(final int prolepticYear, final int dayOfYear) {
    return HebrewDate.ofYearDay(prolepticYear, dayOfYear);
  }

  @Override
  public @NonNull HebrewDate dateYearDay(final @NonNull Era era, final int yearOfEra, final int dayOfYear) {
    return dateYearDay(prolepticYear(era, yearOfEra), dayOfYear);
  }

  @Override
  public @NonNull HebrewDate dateEpochDay(final long epochDay) {
    return HebrewDate.ofEpochDay(epochDay);
  }

  @Override
  public @NonNull HebrewDate date(final @NonNull TemporalAccessor temporal) {
    return HebrewDate.from(temporal);
  }

  @Override
  public @NonNull HebrewDate dateNow() {
    return HebrewDate.ofEpochDay(LocalDate.now().toEpochDay());
  }

  @Override
  public boolean isLeapYear(final long prolepticYear) {
    return HebrewDate.isLeapYear((int) prolepticYear);
  }

  @Override
  public int prolepticYear(final @NonNull Era era, final int yearOfEra) {
    if (!(era instanceof HebrewEra)) {
      throw new ClassCastException("Era must be HebrewEra");
    }
    return yearOfEra;
  }

  @Override
  public @NonNull HebrewEra eraOf(final int eraValue) {
    return HebrewEra.of(eraValue);
  }

  @Override
  public @NonNull List<Era> eras() {
    return List.of(HebrewEra.values());
  }

  @Override
  public @NonNull ValueRange range(final @NonNull ChronoField field) {
    return switch (field) {
      case DAY_OF_MONTH -> ValueRange.of(1, 29, 30);
      case DAY_OF_YEAR -> ValueRange.of(1, 353, 385);
      case MONTH_OF_YEAR -> ValueRange.of(1, 12, 13);
      case ERA -> ValueRange.of(1, 1);
      default -> field.range();
    };
  }
}
