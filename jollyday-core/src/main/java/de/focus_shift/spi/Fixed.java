package de.focus_shift.spi;

import java.time.MonthDay;

public interface Fixed extends Described, Movable, Limited {
  MonthDay day();
}
