package de.focus_shift.jollyday.core.spi;

import org.jspecify.annotations.NonNull;

/**
 * Represents the configuration of an islamic holiday that can be
 *
 * <ul>
 *   <li>Movable</li>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * and has a special {@link IslamicHolidayType}.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.IslamicHolidayParser} is used.
 */
public interface IslamicHolidayConfiguration extends Described, Limited, Movable {

  enum IslamicHolidayType {
    NEWYEAR,
    ASCHURA,
    TASUA,
    MAWLID_AN_NABI,
    LAILAT_AL_MIRAJ,
    LAILAT_AL_BARAT,
    RAMADAN,
    JUMUATUL_WIDA,
    LAILAT_AL_QADR,
    RAMADAN_END,
    ID_AL_FITR,
    ID_AL_FITR_2,
    ID_AL_FITR_3,
    ARAFAAT,
    ID_UL_ADHA,
    ID_UL_ADHA_2,
    ID_UL_ADHA_3,
    ID_AL_GHADIR,
    GRAND_MAGAL_OF_TOUBA,
    QAUMEE_DHUVAS,
    EMBRACING_OF_ISLAM_DAY
  }

  /**
   * Describes the {@link IslamicHolidayType}
   *
   * @return the type of the islamic holiday
   */
  @NonNull IslamicHolidayType type();

  @Override
  default @NonNull String descriptionPropertiesKeyPrefix() {
    return "islamic";
  }
}
