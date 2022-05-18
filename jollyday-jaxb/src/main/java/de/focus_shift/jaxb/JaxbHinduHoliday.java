package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.HinduHoliday;
import de.focus_shift.spi.HinduHolidayType;
import de.focus_shift.spi.YearCycle;

import java.time.Year;

public class JaxbHinduHoliday implements HinduHoliday {

  private final de.focus_shift.jaxb.mapping.HinduHoliday hinduHoliday;

  public JaxbHinduHoliday(de.focus_shift.jaxb.mapping.HinduHoliday hinduHoliday) {
    this.hinduHoliday = hinduHoliday;
  }

  @Override
  public String descriptionPropertiesKey() {
    return hinduHoliday.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return hinduHoliday.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(hinduHoliday.getLocalizedType().name());
  }

  @Override
  public HinduHolidayType type() {
    return HinduHolidayType.valueOf(hinduHoliday.getType().name());
  }

  @Override
  public Year validFrom() {
    return hinduHoliday.getValidFrom() == null
      ? null
      : Year.of(hinduHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return hinduHoliday.getValidTo() == null
      ? null
      : Year.of(hinduHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return hinduHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(hinduHoliday.getEvery());
  }
}
