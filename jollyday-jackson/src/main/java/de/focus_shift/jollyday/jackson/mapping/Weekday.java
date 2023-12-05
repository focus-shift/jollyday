package de.focus_shift.jollyday.jackson.mapping;

public enum Weekday {

  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  public String value() {
    return name();
  }

  public static Weekday fromValue(String v) {
    return valueOf(v);
  }

}
