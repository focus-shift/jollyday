package de.focus_shift.jackson.mapping;

public enum IslamicHolidayType {

  NEWYEAR,
  ASCHURA,
  MAWLID_AN_NABI,
  LAILAT_AL_MIRAJ,
  LAILAT_AL_BARAT,
  RAMADAN,
  LAILAT_AL_QADR,
  RAMADAN_END,
  ID_AL_FITR,
  ID_AL_FITR_2,
  ID_AL_FITR_3,
  ARAFAAT,
  ID_UL_ADHA,
  ID_UL_ADHA_2,
  ID_UL_ADHA_3;

  public String value() {
    return name();
  }

  public static IslamicHolidayType fromValue(String v) {
    return valueOf(v);
  }

}
