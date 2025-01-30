package de.focus_shift.jollyday.pojo;

import java.time.Year;
import java.util.Collections;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Movable.MovingCondition;

public abstract class DefaultMovingHoliday extends DefaultHoliday {

  private List<MovingCondition> conditions;

  public DefaultMovingHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.conditions = conditions;
  }

  public List<MovingCondition> conditions() {
    return conditions != null ? conditions : Collections.emptyList();
  }
}
