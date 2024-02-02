package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.YearCycle;

public class JavaFixedWeekdayInMonth extends DefaultHoliday implements FixedWeekdayInMonth {

  private DayOfWeek weekday;
  private Month month;
  private Occurrance which;

  public JavaFixedWeekdayInMonth(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, DayOfWeek weekday, Month month, Occurrance which) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.weekday = weekday;
    this.month = month;
    this.which = which;
  }

  @Override
  public DayOfWeek weekday() {
    return weekday;
  }

  @Override
  public Month month() {
    return month;
  }

  @Override
  public Occurrance which() {
    return which;
  }


}
