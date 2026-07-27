package de.focus_shift.jollyday.core.chrono;

/**
 * Pure Hebrew (Jewish lunisolar) calendar arithmetic, converting between Hebrew calendar
 * dates and the fixed epoch-day representation used by {@code java.time}.
 * <p>
 * Months are numbered in the traditional ecclesiastical order starting with Nisan:
 * Nisan=1, Iyar=2, Sivan=3, Tammuz=4, Av=5, Elul=6, Tishrei=7, Cheshvan=8, Kislev=9,
 * Tevet=10, Shevat=11, Adar=12, Adar II=13 (only present in leap years).
 * <p>
 * The underlying algorithm (molad calculation and the four Rosh Hashanah postponement
 * rules) is the classic Hebrew calendar algorithm widely used for civil calendar
 * conversion, cross-validated here against independently published Hebrew/Gregorian
 * date pairs spanning several centuries.
 */
final class HebrewCalendarMath {

  static final int NISAN = 1;
  static final int IYAR = 2;
  static final int SIVAN = 3;
  static final int TAMMUZ = 4;
  static final int AV = 5;
  static final int ELUL = 6;
  static final int TISHREI = 7;
  static final int CHESHVAN = 8;
  static final int KISLEV = 9;
  static final int TEVET = 10;
  static final int SHEVAT = 11;
  static final int ADAR = 12;
  static final int ADAR_II = 13;

  /**
   * Integer part of the fixed-day number of 1 Tishrei, year 1 (the traditional epoch).
   */
  private static final long HEBREW_EPOCH = 347_995L;

  /**
   * Fixed-day number of the {@code java.time} epoch day 0 (1970-01-01, ISO/Gregorian).
   */
  private static final long GREGORIAN_EPOCH_DAY_OFFSET = 2_440_587L;

  private HebrewCalendarMath() {
  }

  static boolean isLeapYear(final int year) {
    return Math.floorMod((year * 7L) + 1, 19) < 7;
  }

  static int monthsInYear(final int year) {
    return isLeapYear(year) ? ADAR_II : ADAR;
  }

  private static long delay1(final int year) {
    final long months = Math.floorDiv((235L * year) - 234, 19);
    final long parts = 12_084 + (13_753 * months);
    long day = (months * 29) + Math.floorDiv(parts, 25_920);
    if (Math.floorMod(3 * (day + 1), 7) < 3) {
      day += 1;
    }
    return day;
  }

  private static long delay2(final int year) {
    final long last = delay1(year - 1);
    final long present = delay1(year);
    final long next = delay1(year + 1);
    if (next - present == 356) {
      return 2;
    }
    if (present - last == 382) {
      return 1;
    }
    return 0;
  }

  private static long daysInYear(final int year) {
    return toFixedDay(year + 1, TISHREI, 1) - toFixedDay(year, TISHREI, 1);
  }

  static int lengthOfMonth(final int year, final int month) {
    if (month > ADAR_II) {
      throw new IllegalArgumentException("Invalid Hebrew month: " + month);
    }
    if (month == IYAR || month == TAMMUZ || month == ELUL || month == TEVET || month == ADAR_II) {
      return 29;
    }
    if (month == ADAR && !isLeapYear(year)) {
      return 29;
    }
    if (month == CHESHVAN && Math.floorMod(daysInYear(year), 10) != 5) {
      return 29;
    }
    if (month == KISLEV && Math.floorMod(daysInYear(year), 10) == 3) {
      return 29;
    }
    return 30;
  }

  static int lengthOfYear(final int year) {
    return (int) daysInYear(year);
  }

  /**
   * Converts a Hebrew calendar date to a fixed-day number (an internal day count,
   * only meaningful relative to another value produced by this same method).
   */
  static long toFixedDay(final int year, final int month, final int day) {
    final int monthsInYear = monthsInYear(year);
    long fixedDay = HEBREW_EPOCH + delay1(year) + delay2(year) + day + 1;
    if (month < TISHREI) {
      for (int m = TISHREI; m <= monthsInYear; m++) {
        fixedDay += lengthOfMonth(year, m);
      }
      for (int m = NISAN; m < month; m++) {
        fixedDay += lengthOfMonth(year, m);
      }
    } else {
      for (int m = TISHREI; m < month; m++) {
        fixedDay += lengthOfMonth(year, m);
      }
    }
    return fixedDay;
  }

  /**
   * Converts a fixed-day number (as produced by {@link #toFixedDay(int, int, int)}) back
   * into a Hebrew calendar date.
   *
   * @return an {@code int[]}{year, month, day}
   */
  static int[] fromFixedDay(final long fixedDay) {
    long count = Math.floorDiv((fixedDay - HEBREW_EPOCH) * 98_496L, 35_975_351L);
    int year = (int) count - 1;
    long i = count;
    while (fixedDay >= toFixedDay((int) i, TISHREI, 1)) {
      i += 1;
      year += 1;
    }
    final int first = fixedDay < toFixedDay(year, NISAN, 1) ? TISHREI : NISAN;
    int month = first;
    int m = first;
    while (fixedDay > toFixedDay(year, m, lengthOfMonth(year, m))) {
      m += 1;
      month += 1;
    }
    final int day = (int) (fixedDay - toFixedDay(year, month, 1)) + 1;
    return new int[]{year, month, day};
  }

  static long toEpochDay(final int year, final int month, final int day) {
    return toFixedDay(year, month, day) - GREGORIAN_EPOCH_DAY_OFFSET;
  }

  static int[] fromEpochDay(final long epochDay) {
    return fromFixedDay(epochDay + GREGORIAN_EPOCH_DAY_OFFSET);
  }
}
