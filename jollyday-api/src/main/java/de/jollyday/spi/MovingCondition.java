package de.jollyday.spi;

import java.time.DayOfWeek;

/**
 * @author sdiedrichsen
 * @version $
 * @since 10.03.20
 */
public interface MovingCondition {
  DayOfWeek substitute();

  With with();

  DayOfWeek weekday();
}
