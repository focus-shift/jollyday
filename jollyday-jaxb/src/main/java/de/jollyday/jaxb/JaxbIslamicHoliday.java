package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.IslamicHoliday;
import de.jollyday.spi.IslamicHolidayType;
import de.jollyday.spi.YearCycle;

import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbIslamicHoliday implements IslamicHoliday {

  private final de.jollyday.jaxb.mapping.IslamicHoliday islamicHoliday;

  public JaxbIslamicHoliday(de.jollyday.jaxb.mapping.IslamicHoliday christianHoliday) {
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
    return Year.of(islamicHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(islamicHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return islamicHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(islamicHoliday.getEvery());
  }
}
