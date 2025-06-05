package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbFixedWeekdayInMonthTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
        fixedWeekdayInMonth.setWeekday(Weekday.MONDAY);
        fixedWeekdayInMonth.setMonth(Month.MARCH);
        fixedWeekdayInMonth.setWhich(Which.FIRST);
        fixedWeekdayInMonth.setEvery(HolidayCycleType.ODD_YEARS);
        fixedWeekdayInMonth.setDescriptionPropertiesKey("weekdayinmonth.description");
        fixedWeekdayInMonth.setLocalizedType(HolidayType.BANK_HOLIDAY);
        fixedWeekdayInMonth.setValidFrom(2012);
        fixedWeekdayInMonth.setValidTo(2022);

        final JaxbFixedWeekdayInMonth jaxbFixedWeekdayInMonth = new JaxbFixedWeekdayInMonth(fixedWeekdayInMonth);
        assertThat(jaxbFixedWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
        assertThat(jaxbFixedWeekdayInMonth.month()).isEqualTo(java.time.Month.MARCH);
        assertThat(jaxbFixedWeekdayInMonth.which()).isEqualTo(Occurrence.FIRST);
        assertThat(jaxbFixedWeekdayInMonth.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbFixedWeekdayInMonth.descriptionPropertiesKey()).isEqualTo("weekdayinmonth.description");
        assertThat(jaxbFixedWeekdayInMonth.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jaxbFixedWeekdayInMonth.validFrom()).isEqualTo(Year.of(2012));
        assertThat(jaxbFixedWeekdayInMonth.validTo()).isEqualTo(Year.of(2022));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final FixedWeekdayInMonth mapping = new FixedWeekdayInMonth();
        mapping.setWeekday(Weekday.FRIDAY);
        mapping.setMonth(Month.JUNE);
        mapping.setWhich(Which.LAST);

        final JaxbFixedWeekdayInMonth holiday = new JaxbFixedWeekdayInMonth(mapping);
        assertThat(holiday.weekday()).isEqualTo(DayOfWeek.FRIDAY);
        assertThat(holiday.month()).isEqualTo(java.time.Month.JUNE);
        assertThat(holiday.which()).isEqualTo(Occurrence.LAST);
        assertThat(holiday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(holiday.descriptionPropertiesKey()).isNull();
        assertThat(holiday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
        assertThat(holiday.validFrom()).isNull();
        assertThat(holiday.validTo()).isNull();
    }
}
