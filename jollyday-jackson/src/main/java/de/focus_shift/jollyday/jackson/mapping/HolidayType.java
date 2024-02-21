package de.focus_shift.jollyday.jackson.mapping;

public enum HolidayType {

  PUBLIC_HOLIDAY,
  BANK_HOLIDAY,
  OBSERVANCE,
  UNOFFICIAL_HOLIDAY;

  public String value() {
    return name();
  }

  public static HolidayType fromValue(String v) {
    return valueOf(v);
  }

}
