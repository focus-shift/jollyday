package de.focus_shift.jollyday.jackson.mapping;

public enum When {

  BEFORE,
  AFTER,
  CLOSEST;

  public String value() {
    return name();
  }

  public static When fromValue(String v) {
    return valueOf(v);
  }

}
