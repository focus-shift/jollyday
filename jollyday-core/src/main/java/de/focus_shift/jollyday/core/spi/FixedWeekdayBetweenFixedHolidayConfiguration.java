package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;
import org.jspecify.annotations.NonNull;

/**
 * Represents the configuration of a fixed weekday holiday between two other {@link
 * FixedHolidayConfiguration} holidays/days that can be
 *
 * <ul>
 *   <li>Limited
 *   <li>Described
 * </ul>
 *
 * <p>The `from` and `to` describes the date range in which the `weekday` is a holiday.
 *
 * <p>Example: The holiday is the Saturday between the first January and the 10th January. If there
 * are multiple Saturdays between the two dates all will be returned.
 *
 * <p>The {@link de.focus_shift.jollyday.core.parser.impl.FixedWeekdayBetweenFixedParser} is used.
 */
public interface FixedWeekdayBetweenFixedHolidayConfiguration extends Described, Limited {

  /**
   * Start of the date range in which the weekday occurs
   *
   * @return the {@link FixedHolidayConfiguration} start date of the date range
   */
  @NonNull FixedHolidayConfiguration from();

  /**
   * End of the date range in which the weekday occurs
   *
   * @return the {@link FixedHolidayConfiguration} end date of the date range
   */
  @NonNull FixedHolidayConfiguration to();

  /**
   * The weekday which represents the holiday between from and to
   *
   * @return the weekday for the holiday
   */
  @NonNull DayOfWeek weekday();
}
