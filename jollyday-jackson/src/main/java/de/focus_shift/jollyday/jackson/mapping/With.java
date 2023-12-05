package de.focus_shift.jollyday.jackson.mapping;

public enum With {

  NEXT,
  PREVIOUS;

  public String value() {
    return name();
  }

  public static With fromValue(String v) {
    return valueOf(v);
  }

}
