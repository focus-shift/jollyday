package de.focus_shift.jollyday.jackson.mapping;

public enum ChronologyType {

  JULIAN,
  GREGORIAN;

  public String value() {
    return name();
  }

  public static ChronologyType fromValue(String v) {
    return valueOf(v);
  }

}
