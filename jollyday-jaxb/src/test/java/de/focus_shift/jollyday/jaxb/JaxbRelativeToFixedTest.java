package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.MonthDay;
import java.time.Year;

import static java.time.DayOfWeek.MONDAY;
import static org.assertj.core.api.Assertions.assertThat;

class JaxbRelativeToFixedTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final Fixed fixed = new Fixed();
        fixed.setDay(1);
        fixed.setMonth(Month.JANUARY);

        final RelativeToFixed relativeToFixed = new RelativeToFixed();
        relativeToFixed.setDate(fixed);
        relativeToFixed.setDays(3);
        relativeToFixed.setWeekday(Weekday.MONDAY);
        relativeToFixed.setWhen(When.AFTER);
        relativeToFixed.setEvery(HolidayCycleType.ODD_YEARS);
        relativeToFixed.setDescriptionPropertiesKey("relative.fixed.description");
        relativeToFixed.setLocalizedType(de.focus_shift.jollyday.jaxb.mapping.HolidayType.BANK_HOLIDAY);
        relativeToFixed.setValidFrom(2000);
        relativeToFixed.setValidTo(2020);

        final JaxbRelativeToFixed jaxbRelativeToFixed = new JaxbRelativeToFixed(relativeToFixed);
        assertThat(jaxbRelativeToFixed.date().day()).isEqualTo(MonthDay.of(java.time.Month.JANUARY, 1));
        assertThat(jaxbRelativeToFixed.days()).isEqualTo(Days.of(3));
        assertThat(jaxbRelativeToFixed.weekday()).isEqualTo(MONDAY);
        assertThat(jaxbRelativeToFixed.when()).isEqualTo(Relation.AFTER);
        assertThat(jaxbRelativeToFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbRelativeToFixed.descriptionPropertiesKey()).isEqualTo("relative.fixed.description");
        assertThat(jaxbRelativeToFixed.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(jaxbRelativeToFixed.validFrom()).isEqualTo(Year.of(2000));
        assertThat(jaxbRelativeToFixed.validTo()).isEqualTo(Year.of(2020));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final Fixed fixed = new Fixed();
        fixed.setDay(10);
        fixed.setMonth(Month.FEBRUARY);

        final RelativeToFixed relativeToFixed = new RelativeToFixed();
        relativeToFixed.setDate(fixed);

        final JaxbRelativeToFixed jaxbRelativeToFixed = new JaxbRelativeToFixed(relativeToFixed);
        assertThat(jaxbRelativeToFixed.date().day()).isEqualTo(MonthDay.of(java.time.Month.FEBRUARY, 10));
        assertThat(jaxbRelativeToFixed.days()).isNull();
        assertThat(jaxbRelativeToFixed.when()).isNull();
        assertThat(jaxbRelativeToFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbRelativeToFixed.descriptionPropertiesKey()).isNull();
        assertThat(jaxbRelativeToFixed.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbRelativeToFixed.validFrom()).isNull();
        assertThat(jaxbRelativeToFixed.validTo()).isNull();
    }
}
