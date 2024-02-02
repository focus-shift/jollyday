package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Year;

import org.threeten.extra.Days;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import de.focus_shift.jollyday.core.spi.YearCycle;

public class JavaRelativeToFixed extends DefaultHoliday implements RelativeToFixed {

  private Fixed date;

  DayOfWeek weekday;
  Relation when;
  Days days;

  public JavaRelativeToFixed(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, Fixed date, DayOfWeek weekday, Relation when, Days days) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.date = date;
    this.weekday = weekday;
    this.when = when;
    this.days = days;
  }

  @Override
  public Fixed date() {
    return date;
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
  public Days days() {
    return days;
  }

}
