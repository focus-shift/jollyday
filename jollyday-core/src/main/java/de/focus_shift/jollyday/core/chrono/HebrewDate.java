package de.focus_shift.jollyday.core.chrono;

import org.jspecify.annotations.NonNull;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

import static de.focus_shift.jollyday.core.chrono.HebrewCalendarMath.NISAN;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoField.EPOCH_DAY;

/**
 * A date in the Hebrew calendar system.
 * <p>
 * This date operates using the {@linkplain HebrewChronology Hebrew calendar}, the
 * traditional lunisolar calendar of Judaism.
 * <p>
 * This class is immutable and thread-safe.
 */
public final class HebrewDate implements ChronoLocalDate, Serializable {

  private static final long serialVersionUID = 1L;

  private final int prolepticYear;
  private final int month;
  private final int day;

  private HebrewDate(final int prolepticYear, final int month, final int day) {
    this.prolepticYear = prolepticYear;
    this.month = month;
    this.day = day;
  }

  static @NonNull HebrewDate of(final int prolepticYear, final int month, final int dayOfMonth) {
    if (prolepticYear < 1) {
      throw new DateTimeException("Invalid Hebrew year: " + prolepticYear);
    }
    if (month < NISAN || month > HebrewCalendarMath.monthsInYear(prolepticYear)) {
      throw new DateTimeException("Invalid Hebrew month: " + month);
    }
    if (dayOfMonth < 1 || dayOfMonth > HebrewCalendarMath.lengthOfMonth(prolepticYear, month)) {
      throw new DateTimeException("Invalid Hebrew day-of-month: " + dayOfMonth);
    }
    return new HebrewDate(prolepticYear, month, dayOfMonth);
  }

  static @NonNull HebrewDate ofYearDay(final int prolepticYear, final int dayOfYear) {
    if (dayOfYear < 1 || dayOfYear > HebrewCalendarMath.lengthOfYear(prolepticYear)) {
      throw new DateTimeException("Invalid Hebrew day-of-year: " + dayOfYear);
    }
    final long tishrei1EpochDay = HebrewCalendarMath.toEpochDay(prolepticYear, HebrewCalendarMath.TISHREI, 1);
    return ofEpochDay(tishrei1EpochDay + dayOfYear - 1);
  }

  static @NonNull HebrewDate ofEpochDay(final long epochDay) {
    final int[] ymd = HebrewCalendarMath.fromEpochDay(epochDay);
    return new HebrewDate(ymd[0], ymd[1], ymd[2]);
  }

  static @NonNull HebrewDate from(final @NonNull TemporalAccessor temporal) {
    if (temporal instanceof HebrewDate hebrewDate) {
      return hebrewDate;
    }
    return ofEpochDay(temporal.getLong(EPOCH_DAY));
  }

  static boolean isLeapYear(final int prolepticYear) {
    return HebrewCalendarMath.isLeapYear(prolepticYear);
  }

  private int getDayOfYear() {
    return (int) (toEpochDay() - HebrewCalendarMath.toEpochDay(prolepticYear, HebrewCalendarMath.TISHREI, 1) + 1);
  }

  private int lengthOfWeek() {
    return 7;
  }

  private HebrewDate resolvePrevious(final int newYear, final int newMonth, final int dayOfMonth) {
    final int validYear = Math.max(newYear, 1);
    final int monthsInYear = HebrewCalendarMath.monthsInYear(validYear);
    final int validMonth = Math.min(Math.max(newMonth, NISAN), monthsInYear);
    final int validDay = Math.min(dayOfMonth, HebrewCalendarMath.lengthOfMonth(validYear, validMonth));
    return new HebrewDate(validYear, validMonth, validDay);
  }

  @Override
  public @NonNull HebrewChronology getChronology() {
    return HebrewChronology.INSTANCE;
  }

  @Override
  public @NonNull HebrewEra getEra() {
    return HebrewEra.AM;
  }

  @Override
  public int lengthOfMonth() {
    return HebrewCalendarMath.lengthOfMonth(prolepticYear, month);
  }

  @Override
  public int lengthOfYear() {
    return HebrewCalendarMath.lengthOfYear(prolepticYear);
  }

  @Override
  public boolean isSupported(final @NonNull TemporalField field) {
    if (field instanceof ChronoField) {
      return field.isDateBased();
    }
    return field != null && field.isSupportedBy(this);
  }

  @Override
  public boolean isSupported(final TemporalUnit unit) {
    if (unit instanceof ChronoUnit chronoUnit) {
      return chronoUnit.isDateBased();
    }
    return unit != null && unit.isSupportedBy(this);
  }

  @Override
  public @NonNull ValueRange range(final @NonNull TemporalField field) {
    if (field instanceof ChronoField) {
      if (!isSupported(field)) {
        throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      }
      return switch ((ChronoField) field) {
        case DAY_OF_MONTH -> ValueRange.of(1, lengthOfMonth());
        case DAY_OF_YEAR -> ValueRange.of(1, lengthOfYear());
        case MONTH_OF_YEAR -> ValueRange.of(NISAN, HebrewCalendarMath.monthsInYear(prolepticYear));
        default -> field.range();
      };
    }
    return field.rangeRefinedBy(this);
  }

  @Override
  public long getLong(final @NonNull TemporalField field) {
    if (field instanceof ChronoField) {
      return switch ((ChronoField) field) {
        case DAY_OF_WEEK -> Math.floorMod(toEpochDay() + 3, 7) + 1;
        case DAY_OF_MONTH -> day;
        case DAY_OF_YEAR -> getDayOfYear();
        case EPOCH_DAY -> toEpochDay();
        case MONTH_OF_YEAR -> month;
        case YEAR_OF_ERA -> prolepticYear;
        case YEAR -> prolepticYear;
        case ERA -> 1;
        default -> throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      };
    }
    return field.getFrom(this);
  }

  @Override
  public @NonNull HebrewDate with(final @NonNull TemporalField field, final long newValue) {
    if (field instanceof ChronoField chronoField) {
      range(chronoField).checkValidValue(newValue, chronoField);
      final int newVal = (int) newValue;
      return switch (chronoField) {
        case DAY_OF_WEEK -> plusDays(newValue - getLong(DAY_OF_WEEK));
        case DAY_OF_MONTH -> resolvePrevious(prolepticYear, month, newVal);
        case DAY_OF_YEAR -> plusDays(newValue - getDayOfYear());
        case EPOCH_DAY -> ofEpochDay(newValue);
        case MONTH_OF_YEAR -> resolvePrevious(prolepticYear, newVal, day);
        case YEAR_OF_ERA, YEAR -> resolvePrevious(newVal, month, day);
        case ERA -> this;
        default -> throw new UnsupportedTemporalTypeException("Unsupported field: " + field);
      };
    }
    return field.adjustInto(this, newValue);
  }

  @Override
  public @NonNull HebrewDate with(final @NonNull TemporalAdjuster adjuster) {
    return (HebrewDate) adjuster.adjustInto(this);
  }

  @Override
  public @NonNull HebrewDate plus(final @NonNull TemporalAmount amount) {
    return (HebrewDate) amount.addTo(this);
  }

  @Override
  public @NonNull HebrewDate plus(final long amountToAdd, final @NonNull TemporalUnit unit) {
    if (unit instanceof ChronoUnit chronoUnit) {
      return switch (chronoUnit) {
        case DAYS -> plusDays(amountToAdd);
        case WEEKS -> plusDays(Math.multiplyExact(amountToAdd, lengthOfWeek()));
        case MONTHS -> plusMonths(amountToAdd);
        case YEARS -> plusYears(amountToAdd);
        case DECADES -> plusYears(Math.multiplyExact(amountToAdd, 10));
        case CENTURIES -> plusYears(Math.multiplyExact(amountToAdd, 100));
        case MILLENNIA -> plusYears(Math.multiplyExact(amountToAdd, 1000));
        default -> throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      };
    }
    return unit.addTo(this, amountToAdd);
  }

  private HebrewDate plusDays(final long days) {
    return days == 0 ? this : ofEpochDay(Math.addExact(toEpochDay(), days));
  }

  private HebrewDate plusMonths(final long months) {
    if (months == 0) {
      return this;
    }
    int newYear = prolepticYear;
    int newMonth = month;
    long remaining = months;
    while (remaining > 0) {
      newMonth++;
      if (newMonth > HebrewCalendarMath.monthsInYear(newYear)) {
        newMonth = NISAN;
        newYear++;
      }
      remaining--;
    }
    while (remaining < 0) {
      newMonth--;
      if (newMonth < NISAN) {
        newYear--;
        newMonth = HebrewCalendarMath.monthsInYear(newYear);
      }
      remaining++;
    }
    return resolvePrevious(newYear, newMonth, day);
  }

  private HebrewDate plusYears(final long years) {
    if (years == 0) {
      return this;
    }
    final int newYear = Math.toIntExact(Math.addExact(prolepticYear, years));
    return resolvePrevious(newYear, month, day);
  }

  @Override
  public @NonNull HebrewDate minus(final @NonNull TemporalAmount amount) {
    return (HebrewDate) amount.subtractFrom(this);
  }

  @Override
  public @NonNull HebrewDate minus(final long amountToSubtract, final @NonNull TemporalUnit unit) {
    return amountToSubtract == Long.MIN_VALUE
      ? plus(Long.MAX_VALUE, unit).plus(1, unit)
      : plus(-amountToSubtract, unit);
  }

  @Override
  public long until(final @NonNull Temporal endExclusive, final @NonNull TemporalUnit unit) {
    final HebrewDate end = HebrewDate.from(endExclusive);
    if (unit instanceof ChronoUnit chronoUnit) {
      return switch (chronoUnit) {
        case DAYS -> end.toEpochDay() - toEpochDay();
        case WEEKS -> (end.toEpochDay() - toEpochDay()) / lengthOfWeek();
        case MONTHS -> monthsUntil(end);
        case YEARS -> monthsUntil(end) / 12;
        case DECADES -> monthsUntil(end) / 120;
        case CENTURIES -> monthsUntil(end) / 1200;
        case MILLENNIA -> monthsUntil(end) / 12000;
        default -> throw new UnsupportedTemporalTypeException("Unsupported unit: " + unit);
      };
    }
    return unit.between(this, end);
  }

  private long monthsUntil(final HebrewDate end) {
    long months = 0;
    int y = prolepticYear;
    int m = month;
    while (y < end.prolepticYear || (y == end.prolepticYear && m < end.month)) {
      m++;
      if (m > HebrewCalendarMath.monthsInYear(y)) {
        m = NISAN;
        y++;
      }
      months++;
    }
    while (y > end.prolepticYear || (y == end.prolepticYear && m > end.month)) {
      m--;
      if (m < NISAN) {
        y--;
        m = HebrewCalendarMath.monthsInYear(y);
      }
      months--;
    }
    if (day < end.day) {
      // not yet completed the last month
    } else if (day > end.day && months > 0) {
      months--;
    } else if (day > end.day && months < 0) {
      months++;
    }
    return months;
  }

  @Override
  public @NonNull ChronoPeriod until(final @NonNull ChronoLocalDate endDateExclusive) {
    final HebrewDate end = HebrewDate.from(endDateExclusive);
    final long totalMonths = monthsUntil(end);
    final long years = totalMonths / 12;
    final int months = (int) (totalMonths % 12);
    final int days = (int) (end.toEpochDay() - plus(totalMonths, ChronoUnit.MONTHS).toEpochDay());
    return getChronology().period(Math.toIntExact(years), months, days);
  }

  @Override
  public long toEpochDay() {
    return HebrewCalendarMath.toEpochDay(prolepticYear, month, day);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof HebrewDate other)) {
      return false;
    }
    return prolepticYear == other.prolepticYear && month == other.month && day == other.day;
  }

  @Override
  public int hashCode() {
    return HebrewChronology.INSTANCE.getId().hashCode() ^ (prolepticYear << 11) ^ (month << 6) ^ day;
  }

  @Override
  public @NonNull String toString() {
    return "Hebrew AM " + prolepticYear + (month < 10 ? "-0" : "-") + month + (day < 10 ? "-0" : "-") + day;
  }
}
