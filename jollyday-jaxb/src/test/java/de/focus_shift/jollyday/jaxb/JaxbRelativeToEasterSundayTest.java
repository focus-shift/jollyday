package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.ChronologyType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToEasterSunday;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.IsoChronology;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbRelativeToEasterSundayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final RelativeToEasterSunday relativeToEasterSunday = new RelativeToEasterSunday();
        relativeToEasterSunday.setDays(10);
        relativeToEasterSunday.setChronology(ChronologyType.JULIAN);
        relativeToEasterSunday.setEvery(HolidayCycleType.ODD_YEARS);
        relativeToEasterSunday.setDescriptionPropertiesKey("easter.description");
        relativeToEasterSunday.setLocalizedType(de.focus_shift.jollyday.jaxb.mapping.HolidayType.BANK_HOLIDAY);
        relativeToEasterSunday.setValidFrom(2015);
        relativeToEasterSunday.setValidTo(2025);

        final JaxbRelativeToEasterSunday jaxbRelativeToEasterSunday = new JaxbRelativeToEasterSunday(relativeToEasterSunday);
        assertThat(jaxbRelativeToEasterSunday.days()).isEqualTo(Days.of(10));
        assertThat(jaxbRelativeToEasterSunday.chronology()).isEqualTo(JulianChronology.INSTANCE);
        assertThat(jaxbRelativeToEasterSunday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
        assertThat(jaxbRelativeToEasterSunday.descriptionPropertiesKey()).isEqualTo("easter.description");
        assertThat(jaxbRelativeToEasterSunday.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(jaxbRelativeToEasterSunday.validFrom()).isEqualTo(Year.of(2015));
        assertThat(jaxbRelativeToEasterSunday.validTo()).isEqualTo(Year.of(2025));
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final RelativeToEasterSunday relativeToEasterSunday = new RelativeToEasterSunday();
        relativeToEasterSunday.setDays(-2);

        final JaxbRelativeToEasterSunday jaxbRelativeToEasterSunday = new JaxbRelativeToEasterSunday(relativeToEasterSunday);
        assertThat(jaxbRelativeToEasterSunday.days()).isEqualTo(Days.of(-2));
        assertThat(jaxbRelativeToEasterSunday.chronology()).isEqualTo(IsoChronology.INSTANCE);
        assertThat(jaxbRelativeToEasterSunday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbRelativeToEasterSunday.descriptionPropertiesKey()).isNull();
        assertThat(jaxbRelativeToEasterSunday.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbRelativeToEasterSunday.validFrom()).isNull();
        assertThat(jaxbRelativeToEasterSunday.validTo()).isNull();
    }
}
