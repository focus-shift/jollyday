package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;

import java.time.Period;
import java.time.Year;
import java.util.function.Predicate;

public class ValidCycle implements Predicate<Limited> {

  private final Year year;

  public ValidCycle(final Year year) {
    this.year = year;
  }

  @Override
  public boolean test(final Limited limited) {
    return switch (limited.cycle()) {
      case EVERY_YEAR -> true;
      case ODD_YEARS -> year.getValue() % 2 != 0;
      case EVEN_YEARS -> year.getValue() % 2 == 0;
      case TWO_YEARS -> isValidWithReferenceYear(limited, Period.ofYears(2));
      case THREE_YEARS -> isValidWithReferenceYear(limited, Period.ofYears(3));
      case FOUR_YEARS -> isValidWithReferenceYear(limited, Period.ofYears(4));
      case FIVE_YEARS -> isValidWithReferenceYear(limited, Period.ofYears(5));
      case SIX_YEARS -> isValidWithReferenceYear(limited, Period.ofYears(6));
    };
  }

  /**
   * Will validate if a given year based on the reference year (validFrom/validTo) is valid based on the cycle strategy.
   * <p>
   * Note: no need to test whether we are in range, as this is already done in {@link ValidFromTo}
   *
   * @param limited    provides the reference years. First we use validFrom and if not given validTo
   * @param cycleYears number of years to start the cycle starting from validFrom/validTo
   * @return true if the given year based on validFrom/validTo and the cycle is valid, otherwise false
   */
  private boolean isValidWithReferenceYear(Limited limited, Period cycleYears) {
    if (limited.validFrom() != null) {
      return (year.minusYears(limited.validFrom().getValue())).getValue() % cycleYears.getYears() == 0;
    } else if (limited.validTo() != null) {
      return (limited.validTo().minusYears(year.getValue())).getValue() % cycleYears.getYears() == 0;
    }

    throw new IllegalArgumentException("Cannot handle cycle type '" + limited.cycle() + "' without any reference year.");
  }
}
