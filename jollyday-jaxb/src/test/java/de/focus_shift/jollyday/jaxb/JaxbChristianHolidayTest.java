package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHoliday.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.jaxb.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jaxb.mapping.ChronologyType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import org.junit.jupiter.api.Test;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.IsoChronology;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbChristianHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final ChristianHoliday christianHoliday = new ChristianHoliday();
        christianHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.ChristianHolidayType.EASTER);
        christianHoliday.setChronology(ChronologyType.JULIAN);
        christianHoliday.setDescriptionPropertiesKey("easter.description");
        christianHoliday.setLocalizedType(de.focus_shift.jollyday.jaxb.mapping.HolidayType.BANK_HOLIDAY);
        christianHoliday.setValidFrom(2010);
        christianHoliday.setValidTo(2020);
        christianHoliday.setEvery(HolidayCycleType.ODD_YEARS);

        final JaxbChristianHoliday holiday = new JaxbChristianHoliday(christianHoliday);
        assertThat(holiday.type()).isEqualTo(ChristianHolidayType.EASTER);
        assertThat(holiday.chronology()).isEqualTo(JulianChronology.INSTANCE);
        assertThat(holiday.descriptionPropertiesKey()).isEqualTo("easter.description");
        assertThat(holiday.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(holiday.validFrom()).isEqualTo(Year.of(2010));
        assertThat(holiday.validTo()).isEqualTo(Year.of(2020));
        assertThat(holiday.cycle()).isEqualTo(YearCycle.ODD_YEARS);

        final List<Movable.MovingCondition> conditions = holiday.conditions();
        assertThat(conditions).isEmpty();
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final ChristianHoliday christianHoliday = new ChristianHoliday();
        christianHoliday.setType(de.focus_shift.jollyday.jaxb.mapping.ChristianHolidayType.GOOD_FRIDAY);

        final JaxbChristianHoliday jaxbChristianHoliday = new JaxbChristianHoliday(christianHoliday);
        assertThat(jaxbChristianHoliday.type()).isEqualTo(ChristianHolidayType.GOOD_FRIDAY);
        assertThat(jaxbChristianHoliday.chronology()).isEqualTo(IsoChronology.INSTANCE);
        assertThat(jaxbChristianHoliday.descriptionPropertiesKey()).isNull();
        assertThat(jaxbChristianHoliday.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jaxbChristianHoliday.validFrom()).isNull();
        assertThat(jaxbChristianHoliday.validTo()).isNull();
        assertThat(jaxbChristianHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jaxbChristianHoliday.conditions()).isEmpty();
    }
}
