package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

/**
 * Represents the configuration of a holiday that occurs on a weekday in relation
 * to a fixed weekday in a month that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example: A holiday on tuesday before the second friday in october
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.RelativeToWeekdayInMonthParser} is used.
 */
public interface RelativeToWeekdayInMonthHolidayConfiguration extends Described, Limited {

  /**
   * Describes the weekday on which the new holiday occurs
   *
   * @return the weekday of the new holiday
   */
  DayOfWeek weekday();

  /**
   * Describes the relation of the new holiday to the `weekdayInMonth`
   *
   * @return the relation
   */
  Relation when();

  /**
   * Describes the fixed weekday in month that is the anchor in relation to the new holiday
   *
   * @return the anchor for the relation to the new holiday
   */
  FixedWeekdayInMonthHolidayConfiguration weekdayInMonth();

}
