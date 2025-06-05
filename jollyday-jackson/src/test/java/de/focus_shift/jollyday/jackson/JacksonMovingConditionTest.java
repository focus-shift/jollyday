package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.jackson.mapping.MovingCondition;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.With;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JacksonMovingConditionTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final MovingCondition movingCondition = new MovingCondition();
    movingCondition.setSubstitute(Weekday.SATURDAY);
    movingCondition.setWith(With.NEXT);
    movingCondition.setWeekday(Weekday.MONDAY);

    final JacksonMovingCondition jacksonMovingCondition = new JacksonMovingCondition(movingCondition);
    assertThat(jacksonMovingCondition.substitute()).isEqualTo(DayOfWeek.SATURDAY);
    assertThat(jacksonMovingCondition.with()).isEqualTo(Movable.MovingCondition.With.NEXT);
    assertThat(jacksonMovingCondition.weekday()).isEqualTo(DayOfWeek.MONDAY);
  }

  @Test
  void ensureNullPointerOnUnsetValues() {
    final MovingCondition movingCondition = new MovingCondition();
    final JacksonMovingCondition jacksonMovingCondition = new JacksonMovingCondition(movingCondition);
    assertThrows(NullPointerException.class, jacksonMovingCondition::substitute);
    assertThrows(NullPointerException.class, jacksonMovingCondition::with);
    assertThrows(NullPointerException.class, jacksonMovingCondition::weekday);
  }
}
