package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.IslamicHoliday;
import de.focus_shift.jollyday.jackson.mapping.MovingCondition;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonIslamicHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final IslamicHoliday islamicHoliday = new IslamicHoliday();
        islamicHoliday.setType(de.focus_shift.jollyday.jackson.mapping.IslamicHolidayType.ID_UL_ADHA);
        islamicHoliday.setEvery(HolidayCycleType.ODD_YEARS);
        islamicHoliday.setDescriptionPropertiesKey("islamic.description");
        islamicHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
        islamicHoliday.setValidFrom(2015);
        islamicHoliday.setValidTo(2025);

        final MovingCondition movingCondition = new MovingCondition();
        islamicHoliday.setMovingCondition(Collections.singletonList(movingCondition));

        final JacksonIslamicHoliday jacksonIslamicHoliday = new JacksonIslamicHoliday(islamicHoliday);
        assertThat(jacksonIslamicHoliday.type()).isEqualTo(IslamicHolidayType.ID_UL_ADHA);
        assertThat(jacksonIslamicHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jacksonIslamicHoliday.descriptionPropertiesKey()).isEqualTo("islamic.description");
        assertThat(jacksonIslamicHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jacksonIslamicHoliday.validFrom()).isEqualTo(Year.of(2015));
        assertThat(jacksonIslamicHoliday.validTo()).isEqualTo(Year.of(2025));
        assertThat(jacksonIslamicHoliday.conditions()).hasSize(1);
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final IslamicHoliday islamicHoliday = new IslamicHoliday();
        islamicHoliday.setType(de.focus_shift.jollyday.jackson.mapping.IslamicHolidayType.ID_UL_ADHA);
        islamicHoliday.setMovingCondition(Collections.emptyList());

        final JacksonIslamicHoliday jacksonIslamicHoliday = new JacksonIslamicHoliday(islamicHoliday);
        assertThat(jacksonIslamicHoliday.type()).isEqualTo(IslamicHolidayType.ID_UL_ADHA);
        assertThat(jacksonIslamicHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jacksonIslamicHoliday.descriptionPropertiesKey()).isEqualTo("islamic.ID_UL_ADHA");
        assertThat(jacksonIslamicHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
        assertThat(jacksonIslamicHoliday.validFrom()).isNull();
        assertThat(jacksonIslamicHoliday.validTo()).isNull();
        assertThat(jacksonIslamicHoliday.conditions()).isEmpty();
    }
}
