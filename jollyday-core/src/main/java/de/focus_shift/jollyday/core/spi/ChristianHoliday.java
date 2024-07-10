package de.focus_shift.jollyday.core.spi;

import java.time.chrono.Chronology;

public interface ChristianHoliday extends Limited, Described, Movable {

  ChristianHolidayType type();

  Chronology chronology();

  @Override
  default String descriptionPropertiesKeyPrefix() {
    return "christian.";
  }
}
