package de.focus_shift.jackson;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.spi.EthiopianOrthodoxHolidayType;
import de.focus_shift.spi.YearCycle;

import java.time.Year;

public class JacksonEthiopianOrthodoxHoliday implements EthiopianOrthodoxHoliday {

  private final de.focus_shift.jackson.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday;

  public JacksonEthiopianOrthodoxHoliday(de.focus_shift.jackson.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday) {
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
  }

  @Override
  public String descriptionPropertiesKey() {
    return ethiopianOrthodoxHoliday.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return ethiopianOrthodoxHoliday.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(ethiopianOrthodoxHoliday.getLocalizedType().name());
  }

  @Override
  public EthiopianOrthodoxHolidayType type() {
    return EthiopianOrthodoxHolidayType.valueOf(ethiopianOrthodoxHoliday.getType().name());
  }

  @Override
  public Year validFrom() {
    return ethiopianOrthodoxHoliday.getValidFrom() == null
      ? null
      : Year.of(ethiopianOrthodoxHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return ethiopianOrthodoxHoliday.getValidTo() == null
      ? null
      : Year.of(ethiopianOrthodoxHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return ethiopianOrthodoxHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(ethiopianOrthodoxHoliday.getEvery().name());
  }
}
