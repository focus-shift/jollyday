package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

class JacksonFixedWeekdayBetweenFixedTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final Fixed from = new Fixed();
        from.setDay(1);
        from.setMonth(Month.JANUARY);

        final Fixed to = new Fixed();
        to.setDay(10);
        to.setMonth(Month.JANUARY);

        final FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed = new FixedWeekdayBetweenFixed();
        fixedWeekdayBetweenFixed.setFrom(from);
        fixedWeekdayBetweenFixed.setTo(to);
        fixedWeekdayBetweenFixed.setWeekday(Weekday.MONDAY);
        fixedWeekdayBetweenFixed.setEvery(HolidayCycleType.ODD_YEARS);
        fixedWeekdayBetweenFixed.setDescriptionPropertiesKey("between.description");
        fixedWeekdayBetweenFixed.setLocalizedType(HolidayType.BANK_HOLIDAY);
        fixedWeekdayBetweenFixed.setValidFrom(2010);
        fixedWeekdayBetweenFixed.setValidTo(2020);

        final JacksonFixedWeekdayBetweenFixed jacksonFixedWeekdayBetweenFixed = new JacksonFixedWeekdayBetweenFixed(fixedWeekdayBetweenFixed);
        assertThat(jacksonFixedWeekdayBetweenFixed.from().day()).isEqualTo(MonthDay.of(JANUARY, 1));
        assertThat(jacksonFixedWeekdayBetweenFixed.to().day()).isEqualTo(MonthDay.of(JANUARY, 10));
        assertThat(jacksonFixedWeekdayBetweenFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jacksonFixedWeekdayBetweenFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jacksonFixedWeekdayBetweenFixed.descriptionPropertiesKey()).isEqualTo("between.description");
        assertThat(jacksonFixedWeekdayBetweenFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jacksonFixedWeekdayBetweenFixed.validFrom()).isEqualTo(Year.of(2010));
        assertThat(jacksonFixedWeekdayBetweenFixed.validTo()).isEqualTo(Year.of(2020));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final Fixed from = new Fixed();
        from.setDay(1);
        from.setMonth(Month.JANUARY);

        final Fixed to = new Fixed();
        to.setDay(10);
        to.setMonth(Month.JANUARY);

        final FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed = new FixedWeekdayBetweenFixed();
        fixedWeekdayBetweenFixed.setFrom(from);
        fixedWeekdayBetweenFixed.setTo(to);
        fixedWeekdayBetweenFixed.setWeekday(Weekday.MONDAY);

        final JacksonFixedWeekdayBetweenFixed jacksonFixedWeekdayBetweenFixed = new JacksonFixedWeekdayBetweenFixed(fixedWeekdayBetweenFixed);
        assertThat(jacksonFixedWeekdayBetweenFixed.from().day()).isEqualTo(MonthDay.of(JANUARY, 1));
        assertThat(jacksonFixedWeekdayBetweenFixed.to().day()).isEqualTo(MonthDay.of(JANUARY, 10));
        assertThat(jacksonFixedWeekdayBetweenFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jacksonFixedWeekdayBetweenFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jacksonFixedWeekdayBetweenFixed.descriptionPropertiesKey()).isNull();
        assertThat(jacksonFixedWeekdayBetweenFixed.holidayType()).isEqualTo(PUBLIC_HOLIDAY);
        assertThat(jacksonFixedWeekdayBetweenFixed.validFrom()).isNull();
        assertThat(jacksonFixedWeekdayBetweenFixed.validTo()).isNull();
    }
}
