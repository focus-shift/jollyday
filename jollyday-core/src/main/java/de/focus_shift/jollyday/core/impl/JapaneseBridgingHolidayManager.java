package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.Holiday;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * JapaneseBridgingHolidayManager class.
 * </p>
 * <p>
 * Adds the Japanese citizens' holiday (国民の休日, Art. 3(3) of the Act on National Holidays):
 * a day that lies between two national holidays is a holiday as well.
 * </p>
 * <ul>
 *   <li>The rule is in force since 27 December 1985, the first citizens' holiday was 4 May 1988.</li>
 *   <li>Only national holidays on their actual date count as neighbours, substitute holidays do not.</li>
 *   <li>Until 2006 a Sunday was never a citizens' holiday.</li>
 * </ul>
 */
public class JapaneseBridgingHolidayManager extends DefaultHolidayManager {

  /**
   * The properties key for japanese bridging holidays.
   */
  private static final String BRIDGING_HOLIDAY_PROPERTIES_KEY = "BRIDGING_HOLIDAY";

  /**
   * The day the citizens' holiday was introduced into the Act on National Holidays.
   */
  private static final LocalDate CITIZENS_HOLIDAY_IN_FORCE = LocalDate.of(1985, DECEMBER, 27);

  /**
   * Until the amendment in force since 2007, a Sunday was excluded from being a citizens' holiday.
   */
  private static final LocalDate SUNDAY_EXCLUSION_REMOVED = LocalDate.of(2007, JANUARY, 1);

  /**
   * {@inheritDoc}
   * <p>
   * Implements the rule which requests if two national holidays have one non holiday
   * between each other than this day is also a holiday.
   */
  @Override
  public @NonNull Set<Holiday> getHolidays(@NonNull final Year year, @NonNull final String... args) {
    final Set<Holiday> holidays = super.getHolidays(year, args);

    final Set<LocalDate> nationalHolidays = holidays.stream()
      .map(Holiday::getActualDate)
      .collect(toSet());

    final Set<LocalDate> nonWorkingDays = new HashSet<>(nationalHolidays);
    holidays.forEach(holiday -> nonWorkingDays.add(holiday.getDate()));

    final Set<Holiday> bridgingHolidays = new HashSet<>();
    for (final LocalDate nationalHoliday : nationalHolidays) {
      final LocalDate bridgingDate = nationalHoliday.plusDays(1);
      if (nationalHolidays.contains(bridgingDate.plusDays(1))
        && !nonWorkingDays.contains(bridgingDate)
        && isCitizensHolidayPossible(bridgingDate)) {
        bridgingHolidays.add(new Holiday(bridgingDate, BRIDGING_HOLIDAY_PROPERTIES_KEY, PUBLIC_HOLIDAY));
      }
    }

    holidays.addAll(bridgingHolidays);
    return holidays;
  }

  private static boolean isCitizensHolidayPossible(final LocalDate date) {
    if (date.isBefore(CITIZENS_HOLIDAY_IN_FORCE)) {
      return false;
    }
    return !date.isBefore(SUNDAY_EXCLUSION_REMOVED) || date.getDayOfWeek() != SUNDAY;
  }
}
