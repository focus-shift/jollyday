package de.focus_shift.jollyday.jackson.mapping;

public enum HolidayType {

  OFFICIAL_HOLIDAY,
  BANK_HOLIDAY,
  SCHOOL_HOLIDAY,
  AUTHORITIES_HOLIDAY,
  UNOFFICIAL_HOLIDAY;

  public String value() {
    return name();
  }

  public static HolidayType fromValue(String v) {
    return valueOf(v);
  }

}
