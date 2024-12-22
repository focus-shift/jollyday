package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

public interface MovingCondition {

  DayOfWeek substitute();

  With with();

  DayOfWeek weekday();

}
