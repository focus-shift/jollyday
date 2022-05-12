package de.focus_shift.spi;

import java.time.DayOfWeek;

public interface RelativeToWeekdayInMonth extends Described, Limited {
  FixedWeekdayInMonth weekdayInMonth();

  DayOfWeek weekday();

  Relation when();
}
