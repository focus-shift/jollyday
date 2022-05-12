package de.focus_shift.spi;

import org.threeten.extra.Days;

import java.time.DayOfWeek;

public interface RelativeToFixed extends Described, Limited {
  Fixed date();

  DayOfWeek weekday();

  Relation when();

  Days days();
}
