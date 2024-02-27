package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.util.CalendarUtil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
  public Set<Holiday> getHolidays(final int year, final String... args) {
    final Set<Holiday> holidays = super.getHolidays(year, args);
    final Set<Holiday> additionalHolidays = new HashSet<>();
    for (Holiday holiday : holidays) {
      final LocalDate twoDaysLater = holiday.getDate().plusDays(2);
      if (CalendarUtil.contains(holidays, twoDaysLater)) {
        final LocalDate bridgingDate = twoDaysLater.minusDays(1);
        additionalHolidays.add(new Holiday(bridgingDate, BRIDGING_HOLIDAY_PROPERTIES_KEY, HolidayType.OFFICIAL_HOLIDAY));
      }
    }
    holidays.addAll(additionalHolidays);
    return holidays;
  }
}
