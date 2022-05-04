package de.jollyday.jaxb;

import de.jollyday.spi.With;

import java.time.DayOfWeek;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbMovingCondition implements de.jollyday.spi.MovingCondition {

  private final de.jollyday.jaxb.mapping.MovingCondition movingCondition;

  public JaxbMovingCondition(de.jollyday.jaxb.mapping.MovingCondition jaxbMovingCondition) {
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
