package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

/**
 * Represents the configuration of a fixed weekday holiday between two other {@link Fixed} holidays/days that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * The `from` and `to` describes the date range in which the `weekday` is a holiday.
 * <p>
 * Example: The holiday is the Saturday between the first January and the 10th January.
 * If there are multiple Saturdays between the two dates all will be returned.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.FixedWeekdayBetweenFixedParser} is used.
 */
public interface FixedWeekdayBetweenFixed extends Described, Limited {

  /**
   * Start of the date range in which the weekday occurs
   *
   * @return the {@link Fixed} start date of the date range
   */
  Fixed from();

  /**
   * End of the date range in which the weekday occurs
   *
   * @return the {@link Fixed} end date of the date range
   */
  Fixed to();

  /**
   * The weekday which represents the holiday between from and to
   *
   * @return the weekday for the holiday
   */
  DayOfWeek weekday();

}
