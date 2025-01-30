package de.focus_shift.jollyday.pojo;

import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;

public abstract class DefaultHoliday {

  private String descriptionPropertiesKey;
  private HolidayType officiality;
  private Year validFrom;
  private Year validTo;
  private YearCycle cycle;

  public DefaultHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle) {
    this.descriptionPropertiesKey = descriptionPropertiesKey;
    this.officiality = officiality;
    this.validFrom = validFrom;
    this.validTo = validTo;
    this.cycle = cycle;
  }

  public String descriptionPropertiesKey() {
    return descriptionPropertiesKey;
  }

  public HolidayType holidayType() {
    return officiality;
  }

  public Year validFrom() {
    return validFrom;
  }

  public Year validTo() {
    return validTo;
  }

  public YearCycle cycle() {
    return cycle;
  }


}
