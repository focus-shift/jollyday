package de.focus_shift.spi;

import java.time.chrono.Chronology;

public interface ChristianHoliday extends Limited, Described, Movable {
  ChristianHolidayType type();

  Chronology chronology();
}
