package de.jollyday.spi;

import java.time.DayOfWeek;
import java.time.Month;

public interface FixedWeekdayInMonth extends Described, Limited {
  DayOfWeek weekday();

  Month month();

  Occurrance which();
}
