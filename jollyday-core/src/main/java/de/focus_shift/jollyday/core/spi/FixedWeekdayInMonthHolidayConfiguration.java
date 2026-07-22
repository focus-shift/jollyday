package de.focus_shift.jollyday.core.spi;

import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Represents the configuration of a holiday that has a fixed weekday in a month based on its occurrence that can be
 *
 * <ul>
 *   <li>Movable</li>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example: The first Saturday in January.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.FixedWeekdayInMonthParser} is used.
 */
public interface FixedWeekdayInMonthHolidayConfiguration extends Described, Limited, Movable {

  /**
   * Describes the day of the week, like Monday, Tuesday, ...
   *
   * @return the weekday on which the holiday occurs
   */
  @NonNull DayOfWeek weekday();

  /**
   * Describes the month in which the holiday will occur.
   *
   * @return the month in which the holiday occurs
   */
  @NonNull Month month();

  /**
   * Describes on which weekday the holiday occurs, like the first or second
   *
   * @return the occurrence
   */
  @NonNull Occurrence which();

  /**
   * Describes whether the holiday is moved one week later (same weekday) if it would otherwise
   * fall within Holy Week, i.e. between (inclusive) the Sunday before Easter Sunday minus 7 days
   * and Easter Sunday itself.
   * <p>
   * Example: Näfelser Fahrt in the canton of Glarus, Switzerland, is the first Thursday in April,
   * unless that Thursday falls within Holy Week, in which case it is observed on the second
   * Thursday in April instead.
   *
   * @return {@code true} if the holiday avoids Holy Week, {@code false} otherwise (default)
   */
  default boolean avoidHolyWeek() {
    return false;
  }

}
