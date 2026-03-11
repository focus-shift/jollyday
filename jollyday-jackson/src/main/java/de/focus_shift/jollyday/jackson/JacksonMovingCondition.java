package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Movable;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;

/**
 * see {@link de.focus_shift.jollyday.core.spi.Movable.MovingCondition}
 */
class JacksonMovingCondition implements Movable.MovingCondition {

  private final de.focus_shift.jollyday.jackson.mapping.MovingCondition movingCondition;

  JacksonMovingCondition(de.focus_shift.jollyday.jackson.mapping.MovingCondition jacksonMovingCondition) {
    this.movingCondition = jacksonMovingCondition;
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
