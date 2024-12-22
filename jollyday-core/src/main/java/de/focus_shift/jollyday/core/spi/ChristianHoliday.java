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
