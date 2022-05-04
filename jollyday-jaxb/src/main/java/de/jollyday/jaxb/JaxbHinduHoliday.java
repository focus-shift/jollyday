package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.HinduHoliday;
import de.jollyday.spi.HinduHolidayType;
import de.jollyday.spi.YearCycle;

import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbHinduHoliday implements HinduHoliday {

  private final de.jollyday.jaxb.mapping.HinduHoliday hinduHoliday;

  public JaxbHinduHoliday(de.jollyday.jaxb.mapping.HinduHoliday hinduHoliday) {
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
    return Year.of(hinduHoliday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(hinduHoliday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return hinduHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(hinduHoliday.getEvery());
  }
}
