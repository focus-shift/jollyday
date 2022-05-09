package de.jollyday.spi;

import java.time.chrono.Chronology;

/**
 * @author sdiedrichsen
 * @version $
 * @since 10.03.20
 */
public interface ChristianHoliday extends Limited, Described, Movable {
  ChristianHolidayType type();

  Chronology chronology();
}
