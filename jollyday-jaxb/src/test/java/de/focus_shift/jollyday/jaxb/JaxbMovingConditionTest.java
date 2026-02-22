package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.jaxb.mapping.MovingCondition;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.With;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JaxbMovingConditionTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final MovingCondition movingCondition = new MovingCondition();
        movingCondition.setSubstitute(Weekday.SATURDAY);
        movingCondition.setWith(With.NEXT);
        movingCondition.setWeekday(Weekday.MONDAY);

        final JaxbMovingCondition jaxbMovingCondition = new JaxbMovingCondition(movingCondition);
        assertThat(jaxbMovingCondition.substitute()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(jaxbMovingCondition.with()).isEqualTo(Movable.MovingCondition.With.NEXT);
        assertThat(jaxbMovingCondition.weekday()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    void ensureNullPointerOnUnsetValues() {
        final MovingCondition movingCondition = new MovingCondition();
        final JaxbMovingCondition jaxbMovingCondition = new JaxbMovingCondition(movingCondition);
        assertThrows(NullPointerException.class, jaxbMovingCondition::substitute);
        assertThrows(NullPointerException.class, jaxbMovingCondition::with);
        assertThrows(NullPointerException.class, jaxbMovingCondition::weekday);
    }
}
