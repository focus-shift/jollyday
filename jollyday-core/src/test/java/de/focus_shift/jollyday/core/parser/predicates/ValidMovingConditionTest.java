package de.focus_shift.jollyday.core.parser.predicates;

import static org.assertj.core.api.Assertions.assertThat;

import de.focus_shift.jollyday.core.spi.Movable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

class ValidMovingConditionTest {

  @Test
  void ensureValidMovingConditionOnlyOnSameSubstitution() {

    final Movable.MovingCondition movingCondition =
        new Movable.MovingCondition() {
          @Override
          public @NonNull DayOfWeek substitute() {
            return DayOfWeek.MONDAY;
          }

          @Override
          public @NonNull With with() {
            return With.NEXT;
          }

          @Override
          public @NonNull DayOfWeek weekday() {
            return DayOfWeek.SUNDAY;
          }
        };

    final boolean isValid =
        new ValidMovingCondition(LocalDate.of(2024, 2, 26)).test(movingCondition);
    assertThat(isValid).isTrue();
  }

  @Test
  void ensureNotValidMovingConditionOnDifferentSubstitution() {

    final Movable.MovingCondition movingCondition =
        new Movable.MovingCondition() {
          @Override
          public @NonNull DayOfWeek substitute() {
            return DayOfWeek.TUESDAY;
          }

          @Override
          public @NonNull With with() {
            return With.NEXT;
          }

          @Override
          public @NonNull DayOfWeek weekday() {
            return DayOfWeek.SUNDAY;
          }
        };

    final boolean isValid =
        new ValidMovingCondition(LocalDate.of(2024, 2, 26)).test(movingCondition);
    assertThat(isValid).isFalse();
  }
}
