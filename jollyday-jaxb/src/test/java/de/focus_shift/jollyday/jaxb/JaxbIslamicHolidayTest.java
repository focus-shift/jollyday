package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import de.focus_shift.jollyday.jaxb.mapping.IslamicHoliday;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbIslamicHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final IslamicHoliday islamicHoliday = new IslamicHoliday();
        islamicHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.IslamicHolidayType.ID_UL_ADHA);
        islamicHoliday.setEvery(HolidayCycleType.ODD_YEARS);
        islamicHoliday.setDescriptionPropertiesKey("islamic.description");
        islamicHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
        islamicHoliday.setValidFrom(2015);
        islamicHoliday.setValidTo(2025);

        final JaxbIslamicHoliday jaxbIslamicHoliday = new JaxbIslamicHoliday(islamicHoliday);
        assertThat(jaxbIslamicHoliday.type()).isEqualTo(IslamicHolidayType.ID_UL_ADHA);
        assertThat(jaxbIslamicHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbIslamicHoliday.descriptionPropertiesKey()).isEqualTo("islamic.description");
        assertThat(jaxbIslamicHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jaxbIslamicHoliday.validFrom()).isEqualTo(Year.of(2015));
        assertThat(jaxbIslamicHoliday.validTo()).isEqualTo(Year.of(2025));
        assertThat(jaxbIslamicHoliday.conditions()).isEmpty();
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final IslamicHoliday islamicHoliday = new IslamicHoliday();
        islamicHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.IslamicHolidayType.ID_UL_ADHA);

        final JaxbIslamicHoliday jaxbIslamicHoliday = new JaxbIslamicHoliday(islamicHoliday);
        assertThat(jaxbIslamicHoliday.type()).isEqualTo(IslamicHolidayType.ID_UL_ADHA);
        assertThat(jaxbIslamicHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbIslamicHoliday.descriptionPropertiesKey()).isEqualTo("islamic.ID_UL_ADHA");
        assertThat(jaxbIslamicHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbIslamicHoliday.validFrom()).isNull();
        assertThat(jaxbIslamicHoliday.validTo()).isNull();
        assertThat(jaxbIslamicHoliday.conditions()).isEmpty();
    }
}
