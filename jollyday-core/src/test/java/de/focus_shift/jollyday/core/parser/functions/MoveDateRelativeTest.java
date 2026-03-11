package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.Movable;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MoveDateRelativeTest {

  @Test
  void ensureToConvertMovableToNextLocalDate() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public @NonNull DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }

      @Override
      public @NonNull With with() {
        return With.NEXT;
      }

      @Override
      public @NonNull DayOfWeek substitute() {
        return DayOfWeek.WEDNESDAY;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final Optional<LocalDate> maybeMovedDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(maybeMovedDate).hasValue(LocalDate.of(2024, MARCH, 3));
  }

  @Test
  void ensureToConvertMovableToPreviousLocalDate() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public @NonNull DayOfWeek substitute() {
        return DayOfWeek.WEDNESDAY;
      }

      @Override
      public @NonNull With with() {
        return With.PREVIOUS;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final Optional<LocalDate> maybeMovedDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(maybeMovedDate).hasValue(LocalDate.of(2024, FEBRUARY, 25));
  }

  @Test
  void ensureToReturnEmptyIfMovingConditionIsFalse() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public @NonNull DayOfWeek substitute() {
        return null;
      }

      @Override
      public @NonNull With with() {
        return null;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return null;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final Optional<LocalDate> maybeMovedDate = new MoveDateRelative(LocalDate.of(2024, 2, 28)).apply(movable);
    assertThat(maybeMovedDate).isEmpty();
  }

  @Test
  void ensureOnInvalidConditionTheMappingWillNotBeApplied() {

    final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
      @Override
      public @NonNull DayOfWeek weekday() {
        return DayOfWeek.SUNDAY;
      }

      @Override
      public @NonNull With with() {
        return With.NEXT;
      }

      @Override
      public @NonNull DayOfWeek substitute() {
        return DayOfWeek.WEDNESDAY;
      }
    };

    final Movable movable = () -> List.of(movingCondition);

    final LocalDate localDate = mock(LocalDate.class);
    when(localDate.getDayOfWeek()).thenReturn(DayOfWeek.SATURDAY);

    new MoveDateRelative(localDate).apply(movable);
    verify(localDate, never()).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
  }
}

