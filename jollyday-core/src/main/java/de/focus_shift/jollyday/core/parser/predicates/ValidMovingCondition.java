package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Movable;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

public class ValidMovingCondition implements Predicate<Movable.MovingCondition> {

  private final LocalDate date;

  public ValidMovingCondition(final LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean test(final Movable.MovingCondition movingCondition) {
    return Objects.equals(date.getDayOfWeek(), movingCondition.substitute());
  }
}
