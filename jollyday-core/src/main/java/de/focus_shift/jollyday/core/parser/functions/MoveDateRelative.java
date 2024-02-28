package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.parser.predicates.ValidMovingCondition;
import de.focus_shift.jollyday.core.spi.Movable;

import java.time.LocalDate;
import java.util.function.Function;

import static de.focus_shift.jollyday.core.spi.With.NEXT;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class MoveDateRelative implements Function<Movable, LocalDate> {

  private final LocalDate date;

  public MoveDateRelative(final LocalDate date) {
    this.date = date;
  }

  @Override
  public LocalDate apply(final Movable movable) {
    return movable.conditions().stream()
      .filter(new ValidMovingCondition(date))
      .map(condition -> date.with(condition.with() == NEXT ? nextOrSame(condition.weekday()) : previousOrSame(condition.weekday())))
      .findFirst().orElse(date);
  }
}
