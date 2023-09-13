package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.ChronologyType;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;


public class JaxbRelativeToEasterSunday implements RelativeToEasterSunday {

  private final de.focus_shift.jollyday.jaxb.mapping.RelativeToEasterSunday relativeToEasterSunday;

  public JaxbRelativeToEasterSunday(de.focus_shift.jollyday.jaxb.mapping.RelativeToEasterSunday relativeToEasterSunday) {
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
    return relativeToEasterSunday.getValidFrom() == null
      ? null
      : Year.of(relativeToEasterSunday.getValidFrom());
  }

  @Override
  public Year validTo() {
    return relativeToEasterSunday.getValidTo() == null
      ? null
      : Year.of(relativeToEasterSunday.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return relativeToEasterSunday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToEasterSunday.getEvery().name());
  }

  @Override
  public Chronology chronology() {
    return relativeToEasterSunday.getChronology() == ChronologyType.JULIAN ? JulianChronology.INSTANCE : IsoChronology.INSTANCE;
  }

  @Override
  public Days days() {
    return Days.of(relativeToEasterSunday.getDays());
  }
}
