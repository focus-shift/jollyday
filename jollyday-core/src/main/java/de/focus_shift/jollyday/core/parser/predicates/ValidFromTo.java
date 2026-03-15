package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;
import java.time.Year;
import java.util.function.Predicate;
import org.jspecify.annotations.NonNull;

public class ValidFromTo implements Predicate<Limited> {

  private final Year year;

  public ValidFromTo(@NonNull final Year year) {
    this.year = year;
  }

  @Override
  public boolean test(@NonNull final Limited limited) {
    final Year validFrom = limited.validFrom();
    final Year validTo = limited.validTo();
    return (validFrom == null || !validFrom.isAfter(year))
        && (validTo == null || !year.isAfter(validTo));
  }
}
