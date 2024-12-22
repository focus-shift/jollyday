package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.HolidayType;

/**
 * Provides the functionality to describe a holiday with a
 * name represented by the `descriptionPropertiesKey` and a
 * `officiality` holiday type that can be one of the provides {@link HolidayType}s
 */
public interface Described {

  /**
   * The properties key will be used to translate the holiday into the requested language
   *
   * @return the properties key to use to translate
   */
  String descriptionPropertiesKey();

  /**
   * Represents the type of the holiday.
   * Different holiday types are available in {@link HolidayType}
   *
   * @return the holiday type
   */
  HolidayType officiality();
}
