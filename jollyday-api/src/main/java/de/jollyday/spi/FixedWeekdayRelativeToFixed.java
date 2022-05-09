package de.jollyday.spi;

import java.time.DayOfWeek;

/**
 * @author sdiedrichsen
 * @version $
 * @since 03.11.19
 */
public interface FixedWeekdayRelativeToFixed extends Described, Limited {
  Fixed day();

  Occurrance which();

  DayOfWeek weekday();

  Relation when();
}
