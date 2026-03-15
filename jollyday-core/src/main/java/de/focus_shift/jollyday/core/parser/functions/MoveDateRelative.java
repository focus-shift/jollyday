package de.focus_shift.jollyday.core.parser.functions;

import static de.focus_shift.jollyday.core.spi.Movable.MovingCondition.With.NEXT;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import de.focus_shift.jollyday.core.parser.predicates.ValidMovingCondition;
import de.focus_shift.jollyday.core.spi.Movable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;
import org.jspecify.annotations.NonNull;

public class MoveDateRelative implements Function<Movable, Optional<LocalDate>> {

  private final LocalDate date;

  public MoveDateRelative(@NonNull final LocalDate date) {
    this.date = date;
  }

  @Override
  public @NonNull Optional<LocalDate> apply(@NonNull final Movable movable) {
    return movable.conditions().stream()
        .filter(new ValidMovingCondition(date))
        .map(
            condition ->
                date.with(
                    condition.with() == NEXT
                        ? nextOrSame(condition.weekday())
                        : previousOrSame(condition.weekday())))
        .findFirst();
  }
}
