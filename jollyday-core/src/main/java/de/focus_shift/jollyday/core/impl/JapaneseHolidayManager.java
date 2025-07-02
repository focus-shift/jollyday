package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.Holiday;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.util.CalendarUtil.contains;

/**
 * <p>
 * JapaneseHolidayManager class.
 * </p>
 */
public class JapaneseHolidayManager extends DefaultHolidayManager {

  /**
   * The properties key for japanese bridging holidays.
   */
  private static final String BRIDGING_HOLIDAY_PROPERTIES_KEY = "BRIDGING_HOLIDAY";

  /**
   * {@inheritDoc}
   * <p>
   * Implements the rule which requests if two holidays have one non holiday
   * between each other than this day is also a holiday.
   */
  @Override
  public Set<Holiday> getHolidays(final Year year, final String... args) {
    final Set<Holiday> holidays = super.getHolidays(year, args);

    final Set<Holiday> bridgingHolidays = new HashSet<>();
    for (final Holiday holiday : holidays) {
      final LocalDate twoDaysLater = holiday.getDate().plusDays(2);
      final LocalDate bridgingDate = twoDaysLater.minusDays(1);
      if (contains(holidays, twoDaysLater) && !contains(holidays, bridgingDate)) {
        bridgingHolidays.add(new Holiday(bridgingDate, BRIDGING_HOLIDAY_PROPERTIES_KEY, PUBLIC_HOLIDAY));
      }
    }

    holidays.addAll(bridgingHolidays);
    return holidays;
  }
}
