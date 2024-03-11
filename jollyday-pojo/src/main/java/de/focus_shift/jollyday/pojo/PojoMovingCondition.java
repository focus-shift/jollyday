package de.focus_shift.jollyday.pojo;

import java.time.DayOfWeek;

import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.With;

public class PojoMovingCondition implements MovingCondition {

  private DayOfWeek substitute;
  private With with;
  private DayOfWeek weekday;

  public PojoMovingCondition(DayOfWeek substitute, With with, DayOfWeek weekday) {
    this.substitute = substitute;
    this.with = with;
    this.weekday = weekday;
  }

  @Override
  public DayOfWeek substitute() {
    return substitute;
  }

  @Override
  public With with() {
    return with;
  }

  @Override
  public DayOfWeek weekday() {
    return weekday;
  }
}
