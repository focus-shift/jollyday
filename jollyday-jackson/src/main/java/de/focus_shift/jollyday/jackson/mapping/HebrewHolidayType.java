package de.focus_shift.jollyday.jackson.mapping;

public enum HebrewHolidayType {

  ROSH_HASHANA,
  ROSH_HASHANA_II,
  YOM_KIPPUR,
  SUKKOT,
  SHEMINI_ATZERET,
  PESACH,
  PESACH_VII,
  SHAVUOT,
  YOM_HAATZMAUT;

  public String value() {
    return name();
  }

  public static HebrewHolidayType fromValue(String v) {
    return valueOf(v);
  }

}
