package de.focus_shift.jollyday.core.spi;

import java.util.List;

public interface Movable {

  /**
   * Describes the different moving conditions that
   * have been configured for a specific holiday.
   *
   * @return the configured {@link MovingCondition} for this holiday
   */
  List<MovingCondition> conditions();

}
