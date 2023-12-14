package de.focus_shift.jackson;

import de.focus_shift.spi.With;

import java.time.DayOfWeek;


public class JacksonMovingCondition implements de.focus_shift.spi.MovingCondition {

  private final de.focus_shift.jackson.mapping.MovingCondition movingCondition;

  public JacksonMovingCondition(de.focus_shift.jackson.mapping.MovingCondition jacksonMovingCondition) {
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
