package de.focus_shift.jollyday.pojo;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;

public class PojoFixed extends DefaultMovingHoliday implements Fixed {

  private MonthDay day;

  public PojoFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, MonthDay day) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle, conditions);
    this.day = day;
  }

  @Override
  public MonthDay day() {
    return day;
  }


}
