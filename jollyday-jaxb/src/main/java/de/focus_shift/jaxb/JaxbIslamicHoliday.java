package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.IslamicHoliday;
import de.focus_shift.spi.IslamicHolidayType;
import de.focus_shift.spi.YearCycle;

import java.time.Year;


public class JaxbIslamicHoliday implements IslamicHoliday {

  private final de.focus_shift.jaxb.mapping.IslamicHoliday islamicHoliday;

  public JaxbIslamicHoliday(de.focus_shift.jaxb.mapping.IslamicHoliday christianHoliday) {
    this.islamicHoliday = christianHoliday;
  }

  @Override
  public IslamicHolidayType type() {
    return IslamicHolidayType.valueOf(islamicHoliday.getType().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return islamicHoliday.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return islamicHoliday.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(islamicHoliday.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return islamicHoliday.getValidFrom() == null
      ? null
      : Year.of(islamicHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return islamicHoliday.getValidTo() == null
      ? null
      : Year.of(islamicHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return islamicHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(islamicHoliday.getEvery());
  }
}
