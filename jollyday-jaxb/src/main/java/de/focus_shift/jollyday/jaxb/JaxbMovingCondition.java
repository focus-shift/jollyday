package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.With;

import java.time.DayOfWeek;


public class JaxbMovingCondition implements MovingCondition {

  private final de.focus_shift.jollyday.jaxb.mapping.MovingCondition movingCondition;

  public JaxbMovingCondition(de.focus_shift.jollyday.jaxb.mapping.MovingCondition jaxbMovingCondition) {
    this.movingCondition = jaxbMovingCondition;
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
