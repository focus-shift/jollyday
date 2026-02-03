package de.focus_shift.jollyday.core.spi;

/**
 * Represents the configuration of an ethiopian orthodox holiday that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * and has a special {@link EthiopianOrthodoxHolidayType}.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.EthiopianOrthodoxHolidayParser} is used.
 */
public interface EthiopianOrthodoxHolidayConfiguration extends Described, Limited {

  enum EthiopianOrthodoxHolidayType {
    TIMKAT,
    ENKUTATASH,
    MESKEL
  }

  /**
   * Describes the {@link EthiopianOrthodoxHolidayType}
   *
   * @return the type of the ethiopian orthodox holiday
   */
  EthiopianOrthodoxHolidayType type();

  @Override
  default String descriptionPropertiesKeyPrefix() {
    return "ethiopian.orthodox";
  }
}
