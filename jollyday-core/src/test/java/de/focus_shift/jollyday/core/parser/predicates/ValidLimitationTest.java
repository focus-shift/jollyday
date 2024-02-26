package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.YearCycle;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class ValidLimitationTest {

  @Test
  void ensureToTestValidFromToAndCycle() {
    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(2001);
      }

      @Override
      public Year validTo() {
        return Year.of(2020);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.TWO_YEARS;
      }
    };

    final boolean isValid = new ValidLimitation(2001).test(limited);
    assertThat(isValid).isTrue();
  }

  @Test
  void ensureToFailIfCycleIsIncorrect() {
    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(2001);
      }

      @Override
      public Year validTo() {
        return Year.of(2020);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.EVEN_YEARS;
      }
    };

    final boolean isValid = new ValidLimitation(2001).test(limited);
    assertThat(isValid).isFalse();
  }

  @Test
  void ensureToFailIfFromToIsIncorrect() {
    final Limited limited = new Limited() {
      @Override
      public Year validFrom() {
        return Year.of(2001);
      }

      @Override
      public Year validTo() {
        return Year.of(2020);
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.EVERY_YEAR;
      }
    };

    final boolean isValid = new ValidLimitation(1999).test(limited);
    assertThat(isValid).isFalse();
  }
}
