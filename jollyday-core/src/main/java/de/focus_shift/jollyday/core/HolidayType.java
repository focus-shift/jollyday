package de.focus_shift.jollyday.core;

/**
 * Type of holiday.
 * Each holiday can be placed in a category and this is represented by this type.
 */
public enum HolidayType {

  PUBLIC_HOLIDAY,

  BANK_HOLIDAY,

  @Deprecated(since = "0.26.0", forRemoval = true)
  UNOFFICIAL_HOLIDAY;
}
