package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;
import java.time.Month;

/**
 * Represents the configuration of a holiday that has a fixed weekday in a month based on its occurrence that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example: The first Saturday in January.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.FixedWeekdayInMonthParser} is used.
 */
public interface FixedWeekdayInMonth extends Described, Limited {

  /**
   * Describes the day of the week, like Monday, Tuesday, ...
   *
   * @return the weekday on which the holiday occurs
   */
  DayOfWeek weekday();

  /**
   * Describes the month in which the holiday will occur.
   *
   * @return the month in which the holiday occurs
   */
  Month month();

  /**
   * Describes on which weekday the holiday occurs, like the first or second
   *
   * @return the occurrence
   */
  Occurrence which();

}
