package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import de.focus_shift.jollyday.jaxb.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbRelativeToWeekdayInMonthTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
        fixedWeekdayInMonth.setMonth(Month.MARCH);
        fixedWeekdayInMonth.setWhich(Which.FIRST);
        fixedWeekdayInMonth.setWeekday(Weekday.FRIDAY);

        final RelativeToWeekdayInMonth relativeToWeekdayInMonth = new RelativeToWeekdayInMonth();
        relativeToWeekdayInMonth.setFixedWeekday(fixedWeekdayInMonth);
        relativeToWeekdayInMonth.setWeekday(Weekday.MONDAY);
        relativeToWeekdayInMonth.setWhen(When.BEFORE);
        relativeToWeekdayInMonth.setDescriptionPropertiesKey("weekdayinmonth.description");
        relativeToWeekdayInMonth.setLocalizedType(de.focus_shift.jollyday.jaxb.mapping.HolidayType.BANK_HOLIDAY);
        relativeToWeekdayInMonth.setValidFrom(2010);
        relativeToWeekdayInMonth.setValidTo(2020);
        relativeToWeekdayInMonth.setEvery(HolidayCycleType.ODD_YEARS);

        final JaxbRelativeToWeekdayInMonth jaxbRelativeToWeekdayInMonth = new JaxbRelativeToWeekdayInMonth(relativeToWeekdayInMonth);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().which()).isEqualTo(Occurrence.FIRST);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().weekday()).isEqualTo(DayOfWeek.FRIDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().month()).isEqualTo(java.time.Month.MARCH);
        assertThat(jaxbRelativeToWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.when()).isEqualTo(Relation.BEFORE);
        assertThat(jaxbRelativeToWeekdayInMonth.descriptionPropertiesKey()).isEqualTo("weekdayinmonth.description");
        assertThat(jaxbRelativeToWeekdayInMonth.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.validFrom()).isEqualTo(Year.of(2010));
        assertThat(jaxbRelativeToWeekdayInMonth.validTo()).isEqualTo(Year.of(2020));
        assertThat(jaxbRelativeToWeekdayInMonth.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
        fixedWeekdayInMonth.setMonth(Month.APRIL);
        fixedWeekdayInMonth.setWhich(Which.FIRST);
        fixedWeekdayInMonth.setWeekday(Weekday.SUNDAY);

        final RelativeToWeekdayInMonth relativeToWeekdayInMonth = new RelativeToWeekdayInMonth();
        relativeToWeekdayInMonth.setFixedWeekday(fixedWeekdayInMonth);
        relativeToWeekdayInMonth.setWeekday(Weekday.MONDAY);
        relativeToWeekdayInMonth.setWhen(When.AFTER);

        final JaxbRelativeToWeekdayInMonth jaxbRelativeToWeekdayInMonth = new JaxbRelativeToWeekdayInMonth(relativeToWeekdayInMonth);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().which()).isEqualTo(Occurrence.FIRST);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().weekday()).isEqualTo(DayOfWeek.SUNDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.weekdayInMonth().month()).isEqualTo(java.time.Month.APRIL);
        assertThat(jaxbRelativeToWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.when()).isEqualTo(Relation.AFTER);
        assertThat(jaxbRelativeToWeekdayInMonth.descriptionPropertiesKey()).isNull();
        assertThat(jaxbRelativeToWeekdayInMonth.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbRelativeToWeekdayInMonth.validFrom()).isNull();
        assertThat(jaxbRelativeToWeekdayInMonth.validTo()).isNull();
        assertThat(jaxbRelativeToWeekdayInMonth.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    }
}
