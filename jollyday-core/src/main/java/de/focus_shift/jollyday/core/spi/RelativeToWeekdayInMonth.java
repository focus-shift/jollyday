package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

public interface RelativeToWeekdayInMonth extends Described, Limited {

  FixedWeekdayInMonth weekdayInMonth();

  DayOfWeek weekday();

  Relation when();

}
