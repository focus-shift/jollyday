package de.focus_shift.jollyday.jackson.mapping;

public enum HolidayCycleType {

  EVERY_YEAR,
  TWO_YEARS,
  THREE_YEARS,
  FOUR_YEARS,
  FIVE_YEARS,
  SIX_YEARS,
  ODD_YEARS,
  EVEN_YEARS;

  public String value() {
    return name();
  }

  public static HolidayCycleType fromValue(String v) {
    return valueOf(v);
  }

}
