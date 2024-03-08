package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;

import java.util.function.Predicate;

/**
 * Evaluates if the provided <code>Holiday</code> instance is valid for the
 * provided year.
 */
public class ValidLimitation implements Predicate<Limited> {

  private final int year;

  public ValidLimitation(final int year) {
    this.year = year;
  }

  /**
   * Evaluates if the provided <code>Holiday</code> instance is valid for the
   * provided year.
   *
   * @return is valid for the year.
   */
  @Override
  public boolean test(final Limited limited) {
    return new ValidFromTo(year).and(new ValidCycle(year)).test(limited);
  }

}
