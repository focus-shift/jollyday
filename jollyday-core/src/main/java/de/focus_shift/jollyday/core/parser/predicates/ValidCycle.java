package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;

import java.util.function.Predicate;

public class ValidCycle implements Predicate<Limited> {

  private final int year;

  public ValidCycle(final int year) {
    this.year = year;
  }

  @Override
  public boolean test(Limited limited) {
    switch (limited.cycle()) {
      case EVERY_YEAR:
        return true;
      case ODD_YEARS:
        return year % 2 != 0;
      case EVEN_YEARS:
        return year % 2 == 0;
      default:

        final int cycleYears;
        switch (limited.cycle()) {
          case TWO_YEARS:
            cycleYears = 2;
            break;
          case THREE_YEARS:
            cycleYears = 3;
            break;
          case FOUR_YEARS:
            cycleYears = 4;
            break;
          case FIVE_YEARS:
            cycleYears = 5;
            break;
          case SIX_YEARS:
            cycleYears = 6;
            break;
          default:
            throw new IllegalArgumentException("Cannot handle unknown cycle type '" + limited.cycle() + "'.");
        }

        if (limited.validFrom() != null) {
          // no need to test whether we are in range, as this is already done in ValidFromTo
          return (year - limited.validFrom().getValue()) % cycleYears == 0;
        } else if (limited.validTo() != null) {
          // no need to test whether we are in range, as this is already done in ValidFromTo
          return (limited.validTo().getValue() - year) % cycleYears == 0;
        } else {
          throw new IllegalArgumentException("Cannot handle cycle type '" + limited.cycle() + "' without any reference year.");
        }

    }
  }
}
