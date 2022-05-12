package de.focus_shift.impl;

import de.focus_shift.Holiday;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static de.focus_shift.HolidayType.OFFICIAL_HOLIDAY;

/**
 * <p>
 * JapaneseHolidayManager class.
 * </p>
 *
 * @author Sven
 * @version $Id: $
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
  public Set<Holiday> getHolidays(int year, final String... args) {
    final Set<Holiday> holidays = super.getHolidays(year, args);
    final Set<Holiday> additionalHolidays = new HashSet<>();
    for (Holiday holiday : holidays) {
      final LocalDate twoDaysLater = holiday.getDate().plusDays(2);
      if (calendarUtil.contains(holidays, twoDaysLater)) {
        final LocalDate bridgingDate = twoDaysLater.minusDays(1);
        additionalHolidays.add(new Holiday(bridgingDate, BRIDGING_HOLIDAY_PROPERTIES_KEY, OFFICIAL_HOLIDAY));
      }
    }
    holidays.addAll(additionalHolidays);
    return holidays;
  }
}
