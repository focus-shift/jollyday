package de.focus_shift.jollyday.core.util;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.Set;

/**
 * Utility class for date operations.
 */
public final class CalendarUtil {

  private CalendarUtil() {
    // ok
  }

  /**
   * Shows if the requested date is contained in the Set of holidays.
   * Calls #contains(holidays, date, null)
   *
   * @param holidays the holidays to search through
   * @param date     the date to look for
   * @return the date is contained in the set of holidays
   */
  public static boolean contains(@NonNull final Set<Holiday> holidays, @NonNull final LocalDate date) {
    return contains(holidays, date, null);
  }

  /**
   * Shows if the requested date is contained in the Set of holidays.
   *
   * @param holidays    a {@link java.util.Set} object.
   * @param date        a {@link LocalDate} object.
   * @param holidayType a {@link HolidayType} object
   * @return contains this date
   */
  public static boolean contains(@NonNull final Set<Holiday> holidays, @NonNull final LocalDate date, @Nullable final HolidayType holidayType) {
    return holidays.stream()
      .anyMatch(holiday -> holiday.getDate().equals(date) && (holidayType == null || holiday.getType() == holidayType));
  }
}
