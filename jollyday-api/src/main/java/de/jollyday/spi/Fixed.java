package de.jollyday.spi;

import java.time.MonthDay;

/**
 * @author sdiedrichsen
 * @version $
 * @since 01.11.19
 */
public interface Fixed extends Described, Movable, Limited {
  MonthDay day();
}
