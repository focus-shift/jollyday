package de.focus_shift.jollyday.core.spi;

/**
 * Represents an ethiopian orthodox holiday that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 *
 * and has a special {@link EthiopianOrthodoxHolidayType}.
 * The {@link de.focus_shift.jollyday.core.parser.impl.EthiopianOrthodoxHolidayParser} is used.
 *
 */
public interface EthiopianOrthodoxHoliday extends Described, Limited {

  /**
   * Describes the {@link EthiopianOrthodoxHolidayType}
   *
   * @return the type of the ethiopian orthodox holiday
   */
  EthiopianOrthodoxHolidayType type();

}
