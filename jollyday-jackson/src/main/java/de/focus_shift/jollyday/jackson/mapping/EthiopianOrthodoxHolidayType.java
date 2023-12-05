package de.focus_shift.jollyday.jackson.mapping;

public enum EthiopianOrthodoxHolidayType {

  TIMKAT,
  ENKUTATASH,
  MESKEL;

  public String value() {
    return name();
  }

  public static EthiopianOrthodoxHolidayType fromValue(String v) {
    return valueOf(v);
  }

}
