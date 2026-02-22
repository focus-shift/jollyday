package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

/**
 * Represents the configuration of a holiday that occurs on a fixed weekday
 * relative to a {@link FixedHolidayConfiguration} holiday that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example: A holiday on the first thursday after the second april.
 * In this example the
 * `first` is the which (occurrence)
 * `thursday` is thr weekday (dayOfWeek)
 * `after` is the when (relation)
 * `day` is the second april (fixed)
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.FixedWeekdayRelativeToFixedParser} is used.
 */
public interface FixedWeekdayRelativeToFixedHolidayConfiguration extends Described, Limited {

  /**
   * Describes the anchor the new holiday
   *
   * @return the fixed holiday
   */
  FixedHolidayConfiguration day();

  /**
   * Describes, based on the anchor (day), on which occurrence the new holiday will occur
   *
   * @return the occurrence, like the first
   */
  Occurrence which();

  /**
   * Describes, based on the anchor (day), on which weekday the new holiday will occur
   *
   * @return the weekday
   */
  DayOfWeek weekday();

  /**
   * Describes, based on the anchor (day), the relation like before, after, ... the new holiday will occur
   *
   * @return the relation to the anchor
   */
  Relation when();

}
