package de.focus_shift.jollyday.core.spi;

import org.jspecify.annotations.NonNull;

/**
 * Represents the configuration of a hebrew (Jewish lunisolar calendar) holiday that can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * and has a special {@link HebrewHolidayType}.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.HebrewHolidayParser} is used.
 */
public interface HebrewHolidayConfiguration extends Described, Limited {

  enum HebrewHolidayType {
    ROSH_HASHANA,
    ROSH_HASHANA_II,
    YOM_KIPPUR,
    SUKKOT,
    SHEMINI_ATZERET,
    PESACH,
    PESACH_VII,
    SHAVUOT,
    YOM_HAATZMAUT
  }

  /**
   * Describes the {@link HebrewHolidayType}
   *
   * @return the type of the hebrew holiday
   */
  @NonNull HebrewHolidayType type();

  @Override
  default @NonNull String descriptionPropertiesKeyPrefix() {
    return "hebrew";
  }
}
