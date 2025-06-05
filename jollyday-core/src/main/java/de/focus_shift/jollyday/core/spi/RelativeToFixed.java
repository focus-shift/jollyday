package de.focus_shift.jollyday.core.spi;

import org.threeten.extra.Days;

import java.time.DayOfWeek;

/**
 * Represents the configuration of a holiday that is in relation to a {@link Fixed} date.
 * <ul>
 *   <li>If only weekday is set it will use the weekday.</li>
 *   <li>If only days is set is not set it will use days.</li>
 *   <li>If both is set it will use the weekday.</li>
 * </ul>
 * <p>
 * and can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example:
 * A holiday on tuesday before the second friday in october
 * A holiday on 3 days after the second friday in october
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.RelativeToFixedParser} is used.
 */
public interface RelativeToFixed extends Described, Limited {

  /**
   * Describes the weekday on which the new holiday occurs
   *
   * @return the weekday of the new holiday
   */
  DayOfWeek weekday();

  /**
   * Describes the days in relation to the fixed date of the new holiday
   *
   * @return the days to shift
   */
  Days days();

  /**
   * Describes the relation of the new holiday to the fixed date
   *
   * @return the relation
   */
  Relation when();

  /**
   * Describes the anchor date for the relation of the new holiday
   *
   * @return anchor date for the new holiday
   */
  Fixed date();

}
