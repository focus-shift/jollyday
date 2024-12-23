package de.focus_shift.jollyday.core.spi;

import java.time.chrono.Chronology;

/**
 * Represents a christian holiday that can be
 *
 * <ul>
 *   <li>Movable</li>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 *
 * and has a special {@link ChristianHolidayType} and a chronology based on {@link Chronology}.
 * Two chronologies are supported:
 * <ul>
 *   <li>ISO</li>
 *   <li>Julian</li>
 * </ul>
 *
 * The {@link de.focus_shift.jollyday.core.parser.impl.ChristianHolidayParser} is used.
 */
public interface ChristianHoliday extends Limited, Described, Movable {

  enum ChristianHolidayType {
    GOOD_FRIDAY,
    EASTER_MONDAY,
    ASCENSION_DAY,
    WHIT_MONDAY,
    CORPUS_CHRISTI,
    MAUNDY_THURSDAY,
    ASH_WEDNESDAY,
    MARDI_GRAS,
    GENERAL_PRAYER_DAY,
    CLEAN_MONDAY,
    SHROVE_MONDAY,
    PENTECOST,
    CARNIVAL,
    EASTER_SATURDAY,
    EASTER_TUESDAY,
    SACRED_HEART,
    EASTER,
    PENTECOST_MONDAY,
    WHIT_SUNDAY
  }

  /**
   * Describes the {@link ChristianHolidayType}
   *
   * @return the type of the christian holiday
   */
  ChristianHolidayType type();

  /**
   * Describes the {@link Chronology} of the christian holiday
   *
   * @return the chronology of the christian holiday
   */
  Chronology chronology();
}
