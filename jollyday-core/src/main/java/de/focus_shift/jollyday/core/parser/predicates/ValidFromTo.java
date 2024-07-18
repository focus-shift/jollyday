package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;

import java.time.Year;
import java.util.function.Predicate;

public class ValidFromTo implements Predicate<Limited> {

  private final Year year;

  public ValidFromTo(final Year year) {
    this.year = year;
  }

  @Override
  public boolean test(final Limited limited) {
    return (limited.validFrom() == null || !limited.validFrom().isAfter(year) )
      && (limited.validTo() == null || !year.isAfter(limited.validTo()));
  }
}
