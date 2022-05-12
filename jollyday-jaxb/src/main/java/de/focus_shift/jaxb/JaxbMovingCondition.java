package de.focus_shift.jaxb;

import de.focus_shift.spi.With;

import java.time.DayOfWeek;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbMovingCondition implements de.focus_shift.spi.MovingCondition {

  private final de.focus_shift.jaxb.mapping.MovingCondition movingCondition;

  public JaxbMovingCondition(de.focus_shift.jaxb.mapping.MovingCondition jaxbMovingCondition) {
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
