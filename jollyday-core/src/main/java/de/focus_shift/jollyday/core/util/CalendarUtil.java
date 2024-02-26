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
   * Returns a set of gregorian dates within a gregorian year which equal the
   * islamic month and day. Because the islamic year is about 11 days shorter
   * than the gregorian there may be more than one occurrence of an islamic
   * date in an gregorian year. i.e.: In the gregorian year 2008 there were
   * two 1/1. They occurred on 1/10 and 12/29.
   *
   * @param gregorianYear a int.
   * @param islamicMonth  a int.
   * @param islamicDay    a int.
   * @return List of gregorian dates for the islamic month/day.
   */
  public static Stream<LocalDate> getIslamicHolidaysInGregorianYear(int gregorianYear, int islamicMonth, int islamicDay) {
    return getDatesFromChronologyWithinGregorianYear(islamicMonth, islamicDay, gregorianYear, HijrahChronology.INSTANCE);
  }

  /**
   * Searches for the occurrences of a month/day in one chronology within one
   * gregorian year.
   *
   * @param targetMonth
   * @param targetDay
   * @param gregorianYear
   * @param targetChrono
   * @return the list of gregorian dates.
   */
  private static Stream<LocalDate> getDatesFromChronologyWithinGregorianYear(int targetMonth, int targetDay, int gregorianYear, Chronology targetChrono) {
    return new CalculateRelativeDatesFromChronologyWithinGregorianYear(targetMonth, targetDay, targetChrono, 0).apply(gregorianYear);
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
