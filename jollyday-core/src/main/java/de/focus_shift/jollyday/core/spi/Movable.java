package de.focus_shift.jollyday.core.spi;

import java.util.List;

public interface Movable {
  List<MovingCondition> conditions();
}
