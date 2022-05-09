package de.jollyday.spi;

import de.jollyday.HolidayType;

public interface Described {
  String descriptionPropertiesKey();

  HolidayType officiality();
}
