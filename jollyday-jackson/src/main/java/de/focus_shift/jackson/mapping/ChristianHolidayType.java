package de.focus_shift.jackson.mapping;

public enum ChristianHolidayType {

  GOOD_FRIDAY,
  EASTER_MONDAY,
  ASCENSION_DAY,
  WHIT_MONDAY,
  CORPUS_CHRISTI,
  MAUNDY_THURSDAY,
  ASH_WEDNESDAY,
  MARDI_GRAS,
  GENERAL_PRAYER_DAY,
  CLEAN_MONDAY,
  SHROVE_MONDAY,
  PENTECOST,
  CARNIVAL,
  EASTER_SATURDAY,
  EASTER_TUESDAY,
  SACRED_HEART,
  EASTER,
  PENTECOST_MONDAY,
  WHIT_SUNDAY;

  public String value() {
    return name();
  }

  public static ChristianHolidayType fromValue(String v) {
    return valueOf(v);
  }

}
