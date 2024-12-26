package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Movable;

import java.time.DayOfWeek;

/**
 * see {@link de.focus_shift.jollyday.core.spi.Movable.MovingCondition}
 */
class JaxbMovingCondition implements Movable.MovingCondition {

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
