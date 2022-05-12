package de.focus_shift.parser.predicates;

import de.focus_shift.spi.MovingCondition;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author sdiedrichsen
 * @version $
 * @since 12.03.20
 */
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
