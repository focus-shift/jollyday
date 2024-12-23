package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;

public interface FixedWeekdayRelativeToFixed extends Described, Limited {

  Fixed day();

  Occurrence which();

  DayOfWeek weekday();

  Relation when();

}
