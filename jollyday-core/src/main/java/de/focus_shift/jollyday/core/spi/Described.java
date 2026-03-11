package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.HolidayType;
import org.jspecify.annotations.NonNull;

/**
 * Provides the functionality to describe a holiday with a
 * name represented by the `descriptionPropertiesKey` and a
 * `holidayType` holiday type that can be one of the provides {@link HolidayType}s
 */
public interface Described {

  /**
   * The properties key will be used to translate the holiday into the requested language
   *
   * @return the properties key to use to translate
   */
  @NonNull String descriptionPropertiesKey();

  /**
   * Represents the type of the holiday.
   * Different holiday types are available in {@link HolidayType}
   *
   * @return the holiday type
   */
  @NonNull HolidayType holidayType();

  @NonNull default String descriptionPropertiesKeyPrefix() {
    return "";
  }

  @NonNull default String descriptionPropertiesKeyPrefixSeparator() {
    return ".";
  }
}
