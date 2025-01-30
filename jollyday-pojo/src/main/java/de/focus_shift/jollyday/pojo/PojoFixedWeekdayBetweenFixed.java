package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;

public class PojoFixedWeekdayBetweenFixed extends DefaultHoliday implements FixedWeekdayBetweenFixed {

  private Fixed from;
  private Fixed to;
  private DayOfWeek weekday;

  public PojoFixedWeekdayBetweenFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Fixed from, Fixed to, DayOfWeek weekday) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.from = from;
    this.to = to;
    this.weekday = weekday;
  }

  @Override
  public Fixed from() {
    return from;
  }

  @Override
  public Fixed to() {
    return to;
  }

  @Override
  public DayOfWeek weekday() {
    return weekday;
  }


}
