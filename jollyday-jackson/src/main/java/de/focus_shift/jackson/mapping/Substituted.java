package de.focus_shift.jackson.mapping;

public enum Substituted {

  ON_SATURDAY,
  ON_SUNDAY,
  ON_WEEKEND,
  ON_TUESDAY,
  ON_WEDNESDAY;

  public String value() {
    return name();
  }

  public static Substituted fromValue(String v) {
    return valueOf(v);
  }

}
