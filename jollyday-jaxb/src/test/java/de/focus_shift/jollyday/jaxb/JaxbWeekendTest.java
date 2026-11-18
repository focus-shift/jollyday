package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.Weekend;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbWeekendTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final Weekend weekend = new Weekend();
        weekend.setWeekday(Weekday.FRIDAY);
        weekend.setValidFrom(2010);
        weekend.setValidTo(2020);

        final JaxbWeekend jaxbWeekend = new JaxbWeekend(weekend);
        assertThat(jaxbWeekend.weekday()).isEqualTo(DayOfWeek.FRIDAY);
        assertThat(jaxbWeekend.validFrom()).hasValue(Year.of(2010));
        assertThat(jaxbWeekend.validTo()).hasValue(Year.of(2020));
    }

    @Test
    void ensureToReturnEmptyOptionalsOnNotSetValidityValues() {
        final Weekend weekend = new Weekend();
        weekend.setWeekday(Weekday.SATURDAY);

        final JaxbWeekend jaxbWeekend = new JaxbWeekend(weekend);
        assertThat(jaxbWeekend.weekday()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(jaxbWeekend.validFrom()).isEmpty();
        assertThat(jaxbWeekend.validTo()).isEmpty();
    }
}
