package de.focus_shift.jollyday.core.spi;

import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Optional;

/**
 * Represents a single weekday that is a non-working/rest day (e.g. part of the "weekend")
 * for a {@link HolidayCalendarConfiguration}, optionally limited to a range of years.
 * <p>
 * Example: Saudi Arabia's weekend consists of two {@code WeekendConfiguration} entries,
 * one for {@link DayOfWeek#FRIDAY} and one for {@link DayOfWeek#SATURDAY}.
 */
public interface WeekendConfiguration {

  /**
   * Describes the day of the week that is a non-working/rest day.
   *
   * @return the weekday
   */
  @NonNull DayOfWeek weekday();

  /**
   * Describes the first year this weekday is a non-working/rest day (inclusive)
   *
   * @return the first valid year, empty if valid for every year up to {@link #validTo()}
   */
  @NonNull Optional<Year> validFrom();

  /**
   * Describes the last year this weekday is a non-working/rest day (inclusive)
   *
   * @return the last valid year, empty if valid for every year from {@link #validFrom()} onward
   */
  @NonNull Optional<Year> validTo();

}
