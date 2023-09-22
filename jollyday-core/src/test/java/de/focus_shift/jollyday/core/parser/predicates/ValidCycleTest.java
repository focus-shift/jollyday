package de.focus_shift.jollyday.core.parser.predicates;


import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.YearCycle;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.time.api.constraints.YearRange;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  void ensureLimitedWithCycleYearsWithValidFrom(@ForAll @YearRange(min = 1901) Year year, @ForAll("onlyYearBased") YearCycle cycle) {

    final int modulo = getModulo(cycle);

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(1901);
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return cycle;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    assertThat(validCycle.test(limited)).isEqualTo(((year.getValue() - limited.validFrom().getValue()) % modulo) == 0);
  }

  @Property
  void ensureLimitedForTwoYearReturnsTrueWithValidTo(@ForAll @YearRange(max = 2013) Year year, @ForAll("onlyYearBased") YearCycle cycle) {

    final int modulo = getModulo(cycle);

    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return Year.of(2013);
      }

      @Override
      public YearCycle cycle() {
        return cycle;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    assertThat(validCycle.test(limited)).isEqualTo(((limited.validTo().getValue() - year.getValue()) % modulo) == 0);
  }

  @Provide
  Arbitrary<YearCycle> onlyYearBased() {
    return Arbitraries.of(YearCycle.TWO_YEARS, YearCycle.THREE_YEARS, YearCycle.FOUR_YEARS, YearCycle.FIVE_YEARS, YearCycle.SIX_YEARS);
  }

  private static int getModulo(YearCycle cycle) {
    int modulo = 0;
    switch (cycle){
      case TWO_YEARS:
        modulo = 2;
        break;
      case THREE_YEARS:
        modulo = 3;
        break;
      case FOUR_YEARS:
        modulo = 4;
        break;
      case FIVE_YEARS:
        modulo = 5;
        break;
      case SIX_YEARS:
        modulo = 6;
        break;
    }
    return modulo;
  }

  @Property
  void ensureLimitedForTwoYearReturnsTrueWithoutValidFromAndValidTo(@ForAll @YearRange Year year) {

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
        return YearCycle.TWO_YEARS;
      }
    };

    final ValidCycle validCycle = new ValidCycle(year.getValue());
    final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
      () -> validCycle.test(limited)
    );

    assertThat(thrown.getMessage()).isEqualTo("Cannot handle cycle type 'TWO_YEARS' without any reference year.");
  }
}
