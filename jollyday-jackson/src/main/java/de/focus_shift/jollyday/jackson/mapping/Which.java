package de.focus_shift.jollyday.jackson.mapping;

public enum Which {

  FIRST,
  SECOND,
  THIRD,
  FOURTH,
  LAST;

  public String value() {
    return name();
  }

  public static Which fromValue(String v) {
    return valueOf(v);
  }

}
