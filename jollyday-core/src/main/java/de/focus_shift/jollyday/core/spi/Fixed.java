package de.focus_shift.jollyday.core.spi;

import java.time.MonthDay;

/**
 * Represents the configuration of a holiday that can be
 *
 * <ul>
 *   <li>Movable</li>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * and occurs on the same day and month on every year.
 * <p>
 * Example: A holiday on the first January, like New Year.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.FixedParser} is used.
 */
public interface Fixed extends Described, Movable, Limited {

  /**
   * Contains the information on which day and month this holiday occurs.
   *
   * @return month and day on which this holiday will occur
   */
  MonthDay day();

}
