package de.focus_shift.jollyday.pojo;

import java.time.Year;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.YearCycle;

public class JavaIslamicHoliday extends DefaultMovingHoliday implements IslamicHoliday {

  private IslamicHolidayType type;

  public JavaIslamicHoliday(String descriptionPropertiesKey, HolidayType officiality, Year validFrom, Year validTo, YearCycle cycle, List<MovingCondition> conditions, IslamicHolidayType type) {
    super(descriptionPropertiesKey, officiality, validFrom, validTo, cycle, conditions);
    this.type = type;
  }

  @Override
  public IslamicHolidayType type() {
    return type;
  }

}
