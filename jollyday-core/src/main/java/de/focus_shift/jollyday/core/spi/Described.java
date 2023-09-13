package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.HolidayType;

public interface Described {
  String descriptionPropertiesKey();

  HolidayType officiality();
}
