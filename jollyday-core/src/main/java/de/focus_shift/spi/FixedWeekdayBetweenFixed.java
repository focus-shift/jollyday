package de.focus_shift.spi;

import java.time.DayOfWeek;

public interface FixedWeekdayBetweenFixed extends Described, Limited {
  Fixed from();

  Fixed to();

  DayOfWeek weekday();
}
