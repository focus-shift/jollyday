package de.focus_shift.jollyday.pojo;

import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHoliday;

public class PojoChristianHoliday extends DefaultMovingHoliday implements ChristianHoliday {

  private ChristianHolidayType type;
  private Chronology chronology;

  public PojoChristianHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, ChristianHolidayType type, Chronology chronology) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle, conditions);
    this.type = type;
    this.chronology = chronology;
  }

  @Override
  public ChristianHolidayType type() {
    return type;
  }

  @Override
  public Chronology chronology() {
    return chronology;
  }

}
