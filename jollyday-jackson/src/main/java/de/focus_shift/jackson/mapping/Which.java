package de.focus_shift.jackson.mapping;

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
