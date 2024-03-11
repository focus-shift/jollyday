package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;
import java.time.Year;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.YearCycle;

public class PojoRelativeToWeekdayInMonth extends DefaultHoliday implements RelativeToWeekdayInMonth {

  private FixedWeekdayInMonth weekdayInMonth;
  private DayOfWeek weekday;
  private Relation when;

  public PojoRelativeToWeekdayInMonth(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, FixedWeekdayInMonth weekdayInMonth, DayOfWeek weekday, Relation when) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle);
    this.weekdayInMonth = weekdayInMonth;
    this.weekday = weekday;
    this.when = when;
  }

  @Override
  public FixedWeekdayInMonth weekdayInMonth() {
    return weekdayInMonth;
  }

  @Override
  public DayOfWeek weekday() {
    return weekday;
  }

  @Override
  public Relation when() {
    return when;
  }


}
