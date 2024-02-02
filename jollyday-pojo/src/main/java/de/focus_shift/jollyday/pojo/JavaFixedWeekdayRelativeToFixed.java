package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.YearCycle;


public class JavaFixedWeekdayRelativeToFixed extends DefaultHoliday implements FixedWeekdayRelativeToFixed {

  private DayOfWeek weekday;
  private Relation when;
  private Fixed day;
  private Occurrance which;

  public JavaFixedWeekdayRelativeToFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, DayOfWeek weekday, Relation when, Fixed day, Occurrance which) {
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
  public Occurrance which() {
    return which;
  }


}
