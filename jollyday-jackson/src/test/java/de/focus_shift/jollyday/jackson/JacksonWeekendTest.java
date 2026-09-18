package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.Weekend;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonWeekendTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final Weekend weekend = new Weekend();
        weekend.setWeekday(Weekday.FRIDAY);
        weekend.setValidFrom(2010);
        weekend.setValidTo(2020);

        final JacksonWeekend jacksonWeekend = new JacksonWeekend(weekend);
        assertThat(jacksonWeekend.weekday()).isEqualTo(DayOfWeek.FRIDAY);
        assertThat(jacksonWeekend.validFrom()).hasValue(Year.of(2010));
        assertThat(jacksonWeekend.validTo()).hasValue(Year.of(2020));
    }

    @Test
    void ensureToReturnEmptyOptionalsOnNotSetValidityValues() {
        final Weekend weekend = new Weekend();
        weekend.setWeekday(Weekday.SATURDAY);

        final JacksonWeekend jacksonWeekend = new JacksonWeekend(weekend);
        assertThat(jacksonWeekend.weekday()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(jacksonWeekend.validFrom()).isEmpty();
        assertThat(jacksonWeekend.validTo()).isEmpty();
    }
}
