package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;

import java.util.function.Predicate;

public class ValidFromTo implements Predicate<Limited> {

  private final int year;

  public ValidFromTo(final int year) {
    this.year = year;
  }

  @Override
  public boolean test(Limited limited) {
    return (limited.validFrom() == null || limited.validFrom().getValue() <= year)
      && (limited.validTo() == null || limited.validTo().getValue() >= year);
  }
}
