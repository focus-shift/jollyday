package de.focus_shift.jollyday.core.parser.predicates;

import static org.assertj.core.api.Assertions.assertThat;

import de.focus_shift.jollyday.core.spi.Limited;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;

class ValidLimitationTest {

  @Test
  void ensureToTestValidFromToAndCycle() {
    final Limited limited =
        new Limited() {
          @Override
          public @Nullable Year validFrom() {
            return Year.of(2001);
          }

          @Override
          public @Nullable Year validTo() {
            return Year.of(2020);
          }

          @Override
          public @NonNull YearCycle cycle() {
            return YearCycle.TWO_YEARS;
          }
        };

    final boolean isValid = new ValidLimitation(Year.of(2001)).test(limited);
    assertThat(isValid).isTrue();
  }

  @Test
  void ensureToFailIfCycleIsIncorrect() {
    final Limited limited =
        new Limited() {
          @Override
          public @Nullable Year validFrom() {
            return Year.of(2001);
          }

          @Override
          public @Nullable Year validTo() {
            return Year.of(2020);
          }

          @Override
          public @NonNull YearCycle cycle() {
            return YearCycle.EVEN_YEARS;
          }
        };

    final boolean isValid = new ValidLimitation(Year.of(2001)).test(limited);
    assertThat(isValid).isFalse();
  }

  @Test
  void ensureToFailIfFromToIsIncorrect() {
    final Limited limited =
        new Limited() {
          @Override
          public @Nullable Year validFrom() {
            return Year.of(2001);
          }

          @Override
          public @Nullable Year validTo() {
            return Year.of(2020);
          }

          @Override
          public @NonNull YearCycle cycle() {
            return YearCycle.EVERY_YEAR;
          }
        };

    final boolean isValid = new ValidLimitation(Year.of(1999)).test(limited);
    assertThat(isValid).isFalse();
  }
}
