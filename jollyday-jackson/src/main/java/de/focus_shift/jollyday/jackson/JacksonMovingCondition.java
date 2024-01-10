package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.With;

import java.time.DayOfWeek;

public class JacksonMovingCondition implements MovingCondition {

  private final de.focus_shift.jollyday.jackson.mapping.MovingCondition movingCondition;

  public JacksonMovingCondition(de.focus_shift.jollyday.jackson.mapping.MovingCondition jacksonMovingCondition) {
    this.movingCondition = jacksonMovingCondition;
  }

  @Override
  public DayOfWeek substitute() {
    return DayOfWeek.valueOf(movingCondition.getSubstitute().name());
  }

  @Override
  public With with() {
    return With.valueOf(movingCondition.getWith().name());
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(movingCondition.getWeekday().name());
  }
}
