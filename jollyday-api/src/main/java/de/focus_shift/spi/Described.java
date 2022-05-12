package de.focus_shift.spi;

import de.focus_shift.HolidayType;

public interface Described {
  String descriptionPropertiesKey();

  HolidayType officiality();
}
