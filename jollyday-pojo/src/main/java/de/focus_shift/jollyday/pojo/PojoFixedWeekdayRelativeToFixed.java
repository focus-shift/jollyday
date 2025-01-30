package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;

public class PojoFixedWeekdayRelativeToFixed extends DefaultHoliday implements FixedWeekdayRelativeToFixed {

  private DayOfWeek weekday;
  private Relation when;
  private Fixed day;
  private Occurrence which;

  public PojoFixedWeekdayRelativeToFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, DayOfWeek weekday, Relation when, Fixed day, Occurrence which) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.weekday = weekday;
    this.when = when;
    this.day = day;
    this.which = which;
  }

  @Override
  public DayOfWeek weekday() {
    return weekday;
  }

  @Override
  public Relation when() {
    return when;
  }

  @Override
  public Fixed day() {
    return day;
  }

  @Override
  public Occurrence which() {
    return which;
  }

}
