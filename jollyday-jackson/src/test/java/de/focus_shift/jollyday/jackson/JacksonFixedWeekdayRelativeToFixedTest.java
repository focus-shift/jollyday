package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.When;
import de.focus_shift.jollyday.jackson.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.jackson.mapping.HolidayType.BANK_HOLIDAY;
import static org.assertj.core.api.Assertions.assertThat;

class JacksonFixedWeekdayRelativeToFixedTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final Fixed fixed = new Fixed();
        fixed.setDay(15);
        fixed.setMonth(Month.APRIL);

        final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = new FixedWeekdayRelativeToFixed();
        fixedWeekdayRelativeToFixed.setDay(fixed);
        fixedWeekdayRelativeToFixed.setWeekday(Weekday.THURSDAY);
        fixedWeekdayRelativeToFixed.setWhen(When.BEFORE);
        fixedWeekdayRelativeToFixed.setWhich(Which.SECOND);
        fixedWeekdayRelativeToFixed.setEvery(HolidayCycleType.EVEN_YEARS);
        fixedWeekdayRelativeToFixed.setDescriptionPropertiesKey("relative.description");
        fixedWeekdayRelativeToFixed.setLocalizedType(BANK_HOLIDAY);
        fixedWeekdayRelativeToFixed.setValidFrom(2011);
        fixedWeekdayRelativeToFixed.setValidTo(2021);

        final JacksonFixedWeekdayRelativeToFixed jacksonFixedWeekdayRelativeToFixed = new JacksonFixedWeekdayRelativeToFixed(fixedWeekdayRelativeToFixed);
        assertThat(jacksonFixedWeekdayRelativeToFixed.day().day()).isEqualTo(MonthDay.of(java.time.Month.APRIL, 15));
        assertThat(jacksonFixedWeekdayRelativeToFixed.weekday()).isEqualTo(DayOfWeek.THURSDAY);
        assertThat(jacksonFixedWeekdayRelativeToFixed.when()).isEqualTo(Relation.BEFORE);
        assertThat(jacksonFixedWeekdayRelativeToFixed.which()).isEqualTo(Occurrence.SECOND);
        assertThat(jacksonFixedWeekdayRelativeToFixed.cycle()).isEqualTo(YearCycle.EVEN_YEARS);
        assertThat(jacksonFixedWeekdayRelativeToFixed.descriptionPropertiesKey()).isEqualTo("relative.description");
        assertThat(jacksonFixedWeekdayRelativeToFixed.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(jacksonFixedWeekdayRelativeToFixed.validFrom()).isEqualTo(Year.of(2011));
        assertThat(jacksonFixedWeekdayRelativeToFixed.validTo()).isEqualTo(Year.of(2021));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final Fixed fixed = new Fixed();
        fixed.setDay(5);
        fixed.setMonth(Month.MAY);

        final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = new FixedWeekdayRelativeToFixed();
        fixedWeekdayRelativeToFixed.setDay(fixed);
        fixedWeekdayRelativeToFixed.setWeekday(Weekday.MONDAY);
        fixedWeekdayRelativeToFixed.setWhen(When.AFTER);
        fixedWeekdayRelativeToFixed.setWhich(Which.FIRST);

        final JacksonFixedWeekdayRelativeToFixed jacksonFixedWeekdayRelativeToFixed = new JacksonFixedWeekdayRelativeToFixed(fixedWeekdayRelativeToFixed);
        assertThat(jacksonFixedWeekdayRelativeToFixed.day().day()).isEqualTo(MonthDay.of(java.time.Month.MAY, 5));
        assertThat(jacksonFixedWeekdayRelativeToFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jacksonFixedWeekdayRelativeToFixed.when()).isEqualTo(Relation.AFTER);
        assertThat(jacksonFixedWeekdayRelativeToFixed.which()).isEqualTo(Occurrence.FIRST);
        assertThat(jacksonFixedWeekdayRelativeToFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jacksonFixedWeekdayRelativeToFixed.descriptionPropertiesKey()).isNull();
        assertThat(jacksonFixedWeekdayRelativeToFixed.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jacksonFixedWeekdayRelativeToFixed.validFrom()).isNull();
        assertThat(jacksonFixedWeekdayRelativeToFixed.validTo()).isNull();
    }
}
