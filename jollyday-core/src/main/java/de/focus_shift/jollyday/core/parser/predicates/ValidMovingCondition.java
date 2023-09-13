package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.MovingCondition;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

public class ValidMovingCondition implements Predicate<MovingCondition> {

  private final LocalDate date;

  public ValidMovingCondition(final LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean test(MovingCondition movingCondition) {
    return Objects.equals(date.getDayOfWeek(), movingCondition.substitute());
  }
}
