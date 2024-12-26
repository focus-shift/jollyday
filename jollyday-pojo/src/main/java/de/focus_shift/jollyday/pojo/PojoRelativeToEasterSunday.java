package de.focus_shift.jollyday.pojo;

import java.time.Year;
import java.time.chrono.Chronology;

import org.threeten.extra.Days;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.YearCycle;

public class PojoRelativeToEasterSunday extends DefaultHoliday implements RelativeToEasterSunday {

  private Chronology chronology;
  private Days days;

  public PojoRelativeToEasterSunday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Chronology chronology, Days days) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.chronology = chronology;
    this.days = days;
  }

  @Override
  public Chronology chronology() {
    return chronology;
  }

  @Override
  public Days days() {
    return days;
  }
}
