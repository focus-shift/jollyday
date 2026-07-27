package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration.HebrewHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.HebrewHoliday;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbHebrewHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final HebrewHoliday hebrewHoliday = new HebrewHoliday();
        hebrewHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.HebrewHolidayType.ROSH_HASHANA);
        hebrewHoliday.setEvery(HolidayCycleType.ODD_YEARS);
        hebrewHoliday.setDescriptionPropertiesKey("hebrew.description");
        hebrewHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
        hebrewHoliday.setValidFrom(2010);
        hebrewHoliday.setValidTo(2020);

        final JaxbHebrewHoliday jaxbHebrewHoliday = new JaxbHebrewHoliday(hebrewHoliday);
        assertThat(jaxbHebrewHoliday.type()).isEqualTo(HebrewHolidayType.ROSH_HASHANA);
        assertThat(jaxbHebrewHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbHebrewHoliday.descriptionPropertiesKey()).isEqualTo("hebrew.description");
        assertThat(jaxbHebrewHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jaxbHebrewHoliday.validFrom()).hasValue(Year.of(2010));
        assertThat(jaxbHebrewHoliday.validTo()).hasValue(Year.of(2020));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final HebrewHoliday hebrewHoliday = new HebrewHoliday();
        hebrewHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.HebrewHolidayType.ROSH_HASHANA);

        final JaxbHebrewHoliday jaxbHebrewHoliday = new JaxbHebrewHoliday(hebrewHoliday);
        assertThat(jaxbHebrewHoliday.type()).isEqualTo(HebrewHolidayType.ROSH_HASHANA);
        assertThat(jaxbHebrewHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbHebrewHoliday.descriptionPropertiesKey()).isEqualTo("hebrew.ROSH_HASHANA");
        assertThat(jaxbHebrewHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbHebrewHoliday.validFrom()).isEmpty();
        assertThat(jaxbHebrewHoliday.validTo()).isEmpty();
    }
}
