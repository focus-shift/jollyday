package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.core.spi.With;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ValidMovingConditionTest {

  @Test
  void ensureValidMovingConditionOnlyOnSameSubstitution() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public DayOfWeek substitute() {
        return DayOfWeek.MONDAY;
      }

      @Override
      public With with() {
        return With.NEXT;
      }

      @Override
      public DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }
    };

    final boolean isValid = new ValidMovingCondition(LocalDate.of(2024, 2, 26)).test(movingCondition);
    assertThat(isValid).isTrue();
  }

  @Test
  void ensureNotValidMovingConditionOnDifferentSubstitution() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public DayOfWeek substitute() {
        return DayOfWeek.TUESDAY;
      }

      @Override
      public With with() {
        return With.NEXT;
      }

      @Override
      public DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }
    };

    final boolean isValid = new ValidMovingCondition(LocalDate.of(2024, 2, 26)).test(movingCondition);
    assertThat(isValid).isFalse();
  }
}
