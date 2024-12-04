package de.focus_shift.jollyday.jackson.mapping;

public enum HolidayType {

  PUBLIC_HOLIDAY,
  BANK_HOLIDAY,
  OBSERVANCE;

  public String value() {
    return name();
  }

  public static HolidayType fromValue(String v) {
    return valueOf(v);
  }

}
