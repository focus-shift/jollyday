package de.focus_shift.jollyday.core.parser.predicates;


import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.YearCycle;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class ValidCycleTest {

  @Property
  void ensureLimitedForEveryYearReturnsTrue(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.EVERY_YEAR;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    assertThat(validCycle.test(limited)).isTrue();
  }

  @Property
  void ensureLimitedForOddYearReturnsTrue(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.ODD_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 2 != 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForEvenYearReturnsTrue(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.EVEN_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 2 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForTwoYearReturnsTrueWithValidFromAndValidTo(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1800);
      }

      @Override
      public Year validTo() {
        return Year.of(3000);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.TWO_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 2 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForThreeYearReturnsTrueWithValidFromAndValidTo(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1800);
      }

      @Override
      public Year validTo() {
        return Year.of(3000);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.THREE_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 3 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForFourYearReturnsTrueWithValidFromAndValidTo(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1800);
      }

      @Override
      public Year validTo() {
        return Year.of(3000);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.FOUR_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 4 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForFiveYearReturnsTrueWithValidFromAndValidTo(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1800);
      }

      @Override
      public Year validTo() {
        return Year.of(3000);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.FIVE_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 5 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }

  @Property
  void ensureLimitedForSixYearReturnsTrueWithValidFromAndValidTo(@ForAll @YearRange Year year) {

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1800);
      }

      @Override
      public Year validTo() {
        return Year.of(3000);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.SIX_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    if (year.getValue() % 6 == 0) {
      assertThat(validCycle.test(limited)).isTrue();
    } else {
      assertThat(validCycle.test(limited)).isFalse();
    }
  }
}
