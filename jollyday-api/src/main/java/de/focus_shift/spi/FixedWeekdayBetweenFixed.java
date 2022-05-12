package de.focus_shift.spi;

import java.time.DayOfWeek;

/**
 * @author sdiedrichsen
 * @version $
 * @since 03.11.19
 */
public interface FixedWeekdayBetweenFixed extends Described, Limited {
  Fixed from();

  Fixed to();

  DayOfWeek weekday();
}
