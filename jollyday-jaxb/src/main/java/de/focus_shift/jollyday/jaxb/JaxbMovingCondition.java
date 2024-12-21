package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.With;

import java.time.DayOfWeek;

/**
 * {@inheritDoc}
 */
class JaxbMovingCondition implements MovingCondition {

  private final de.focus_shift.jollyday.jaxb.mapping.MovingCondition movingCondition;

  JaxbMovingCondition(de.focus_shift.jollyday.jaxb.mapping.MovingCondition jaxbMovingCondition) {
    this.movingCondition = jaxbMovingCondition;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek substitute() {
    return DayOfWeek.valueOf(movingCondition.getSubstitute().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public With with() {
    return With.valueOf(movingCondition.getWith().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(movingCondition.getWeekday().name());
  }
}
