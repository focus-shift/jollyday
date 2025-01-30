package de.focus_shift.jollyday.pojo;

import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;

public class PojoEthiopianOrthodoxHoliday extends DefaultHoliday implements EthiopianOrthodoxHoliday {

  private EthiopianOrthodoxHolidayType type;

  public PojoEthiopianOrthodoxHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, EthiopianOrthodoxHolidayType type) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.type = type;
  }

  @Override
  public EthiopianOrthodoxHolidayType type() {
    return type;
  }

}
