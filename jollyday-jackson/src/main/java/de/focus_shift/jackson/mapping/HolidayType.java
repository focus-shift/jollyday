package de.focus_shift.jackson.mapping;

public enum HolidayType {

  OFFICIAL_HOLIDAY,
  UNOFFICIAL_HOLIDAY;

  public String value() {
    return name();
  }

  public static HolidayType fromValue(String v) {
    return valueOf(v);
  }

}
