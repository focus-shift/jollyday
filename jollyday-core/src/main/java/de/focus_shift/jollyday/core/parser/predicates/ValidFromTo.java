package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;
import org.jspecify.annotations.NonNull;

import java.time.Year;
import java.util.Optional;
import java.util.function.Predicate;

public class ValidFromTo implements Predicate<Limited> {

  private final Year year;

  public ValidFromTo(@NonNull final Year year) {
    this.year = year;
  }

  @Override
  public boolean test(@NonNull final Limited limited) {
    final Optional<Year> validFrom = limited.validFrom();
    final Optional<Year> validTo = limited.validTo();
    return (validFrom.isEmpty() || !validFrom.get().isAfter(year)) && (validTo.isEmpty() || !year.isAfter(validTo.get()));
  }
}
