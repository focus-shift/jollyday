package de.focus_shift.jollyday.jackson.mapping;

public enum Month {

  JANUARY,
  FEBRUARY,
  MARCH,
  APRIL,
  MAY,
  JUNE,
  JULY,
  AUGUST,
  SEPTEMBER,
  OCTOBER,
  NOVEMBER,
  DECEMBER;

  public String value() {
    return name();
  }

  public static Month fromValue(String v) {
    return valueOf(v);
  }

}
