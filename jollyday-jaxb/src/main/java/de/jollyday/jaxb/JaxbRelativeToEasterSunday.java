package de.jollyday.jaxb;

import de.jollyday.HolidayType;
import de.jollyday.spi.RelativeToEasterSunday;
import de.jollyday.spi.YearCycle;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;

import static de.jollyday.jaxb.mapping.ChronologyType.JULIAN;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbRelativeToEasterSunday implements RelativeToEasterSunday {

  private final de.jollyday.jaxb.mapping.RelativeToEasterSunday relativeToEasterSunday;

  public JaxbRelativeToEasterSunday(de.jollyday.jaxb.mapping.RelativeToEasterSunday relativeToEasterSunday) {
    this.relativeToEasterSunday = relativeToEasterSunday;
  }

  @Override
  public String descriptionPropertiesKey() {
    return relativeToEasterSunday.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return relativeToEasterSunday.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(relativeToEasterSunday.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return Year.of(relativeToEasterSunday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(relativeToEasterSunday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return relativeToEasterSunday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToEasterSunday.getEvery());
  }

  @Override
  public Chronology chronology() {
    return relativeToEasterSunday.getChronology() == JULIAN ? JulianChronology.INSTANCE : IsoChronology.INSTANCE;
  }

  @Override
  public Days days() {
    return Days.of(relativeToEasterSunday.getDays());
  }
}
