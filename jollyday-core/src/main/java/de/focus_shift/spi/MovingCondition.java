package de.focus_shift.spi;

import java.time.DayOfWeek;

public interface MovingCondition {
  DayOfWeek substitute();

  With with();

  DayOfWeek weekday();
}
