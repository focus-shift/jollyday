package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.parser.predicates.ValidMovingCondition;

import java.time.LocalDate;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class MoveDateRelative implements Function<Movable, LocalDate> {

  private final LocalDate date;

  public MoveDateRelative(LocalDate date) {
    this.date = date;
  }

  @Override
  public LocalDate apply(Movable movable) {
    return movable.conditions().stream()
      .filter(new ValidMovingCondition(date))
      .map(mc -> date.with(mc.with() == With.NEXT
        ? nextOrSame(mc.weekday())
        : previousOrSame(mc.weekday())))
      .findFirst()
      .orElse(date);
  }
}
