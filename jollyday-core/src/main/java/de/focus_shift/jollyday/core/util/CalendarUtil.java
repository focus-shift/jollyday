package de.focus_shift.jollyday.core.util;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;

import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Utility class for date operations.
 */
public class CalendarUtil {

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
  public static boolean contains(final Set<Holiday> holidays, final LocalDate date) {
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
  public static boolean contains(final Set<Holiday> holidays, final LocalDate date, final HolidayType holidayType) {
    return holidays.stream()
      .anyMatch(holiday -> holiday.getDate().equals(date) && (holidayType == null || holiday.getType() == holidayType));
  }
}
