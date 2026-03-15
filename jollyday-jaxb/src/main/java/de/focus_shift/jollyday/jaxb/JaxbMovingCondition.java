package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Movable;
import java.time.DayOfWeek;
import org.jspecify.annotations.NonNull;

/** see {@link de.focus_shift.jollyday.core.spi.Movable.MovingCondition} */
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
  public @NonNull DayOfWeek substitute() {
    return DayOfWeek.valueOf(movingCondition.getSubstitute().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull With with() {
    return With.valueOf(movingCondition.getWith().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(movingCondition.getWeekday().name());
  }
}
