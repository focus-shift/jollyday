package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbEthiopianOrthodoxHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
        ethiopianOrthodoxHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHolidayType.TIMKAT);
        ethiopianOrthodoxHoliday.setEvery(HolidayCycleType.ODD_YEARS);
        ethiopianOrthodoxHoliday.setDescriptionPropertiesKey("ethiopian.description");
        ethiopianOrthodoxHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
        ethiopianOrthodoxHoliday.setValidFrom(2010);
        ethiopianOrthodoxHoliday.setValidTo(2020);

        final JaxbEthiopianOrthodoxHoliday jaxbEthiopianOrthodoxHoliday = new JaxbEthiopianOrthodoxHoliday(ethiopianOrthodoxHoliday);
        assertThat(jaxbEthiopianOrthodoxHoliday.type()).isEqualTo(EthiopianOrthodoxHolidayType.TIMKAT);
        assertThat(jaxbEthiopianOrthodoxHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbEthiopianOrthodoxHoliday.descriptionPropertiesKey()).isEqualTo("ethiopian.description");
        assertThat(jaxbEthiopianOrthodoxHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
        assertThat(jaxbEthiopianOrthodoxHoliday.validFrom()).isEqualTo(Year.of(2010));
        assertThat(jaxbEthiopianOrthodoxHoliday.validTo()).isEqualTo(Year.of(2020));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
        ethiopianOrthodoxHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHolidayType.TIMKAT);

        final JaxbEthiopianOrthodoxHoliday jaxbEthiopianOrthodoxHoliday = new JaxbEthiopianOrthodoxHoliday(ethiopianOrthodoxHoliday);
        assertThat(jaxbEthiopianOrthodoxHoliday.type()).isEqualTo(EthiopianOrthodoxHolidayType.TIMKAT);
        assertThat(jaxbEthiopianOrthodoxHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbEthiopianOrthodoxHoliday.descriptionPropertiesKey()).isNull();
        assertThat(jaxbEthiopianOrthodoxHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbEthiopianOrthodoxHoliday.validFrom()).isNull();
        assertThat(jaxbEthiopianOrthodoxHoliday.validTo()).isNull();
    }
}
