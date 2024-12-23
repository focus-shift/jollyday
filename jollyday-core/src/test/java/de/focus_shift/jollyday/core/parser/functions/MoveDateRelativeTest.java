package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.core.spi.With;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;

class MoveDateRelativeTest {

  @Test
  void ensureToConvertMovableToNextLocalDate() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public DayOfWeek substitute() {
        return DayOfWeek.WEDNESDAY;
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

    final Movable movable = () -> List.of(movingCondition);

    final LocalDate localDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(localDate)
      .hasYear(2024)
      .hasMonth(MARCH)
      .hasDayOfMonth(3);
  }

  @Test
  void ensureToConvertMovableToPreviousLocalDate() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public DayOfWeek substitute() {
        return DayOfWeek.WEDNESDAY;
      }

      @Override
      public With with() {
        return With.PREVIOUS;
      }

      @Override
      public DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final LocalDate localDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(localDate)
      .hasYear(2024)
      .hasMonth(FEBRUARY)
      .hasDayOfMonth(25);
  }

  @Test
  void ensureToReturnProvidedLocalDateIfMovingConditionIsFalse() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public DayOfWeek substitute() {
        return null;
      }

      @Override
      public With with() {
        return null;
      }

      @Override
      public DayOfWeek weekday() {
        return null;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final LocalDate localDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(localDate)
      .hasYear(2024)
      .hasMonth(FEBRUARY)
      .hasDayOfMonth(28);
  }
}
