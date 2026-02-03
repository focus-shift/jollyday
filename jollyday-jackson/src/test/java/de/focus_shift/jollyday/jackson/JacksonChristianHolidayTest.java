package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.jackson.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.MovingCondition;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import org.junit.jupiter.api.Test;
import org.threeten.extra.chrono.JulianChronology;

import java.time.DayOfWeek;
import java.time.Year;
import java.time.chrono.IsoChronology;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonChristianHolidayTest {

    @Test
    void ensureFieldsAreSetAndMappedCorrectly() {
        final ChristianHoliday christianHoliday = new ChristianHoliday();
        christianHoliday.setType(de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.EASTER);
        christianHoliday.setChronology(ChronologyType.JULIAN);
        christianHoliday.setDescriptionPropertiesKey("easter.description");
        christianHoliday.setLocalizedType(de.focus_shift.jollyday.jackson.mapping.HolidayType.BANK_HOLIDAY);
        christianHoliday.setValidFrom(2010);
        christianHoliday.setValidTo(2020);
        christianHoliday.setEvery(HolidayCycleType.ODD_YEARS);

        final MovingCondition movingCondition = new MovingCondition();
        movingCondition.setSubstitute(Weekday.SATURDAY);
        christianHoliday.setMovingCondition(Collections.singletonList(movingCondition));

        final JacksonChristianHoliday holiday = new JacksonChristianHoliday(christianHoliday);
        assertThat(holiday.type()).isEqualTo(ChristianHolidayType.EASTER);
        assertThat(holiday.chronology()).isEqualTo(JulianChronology.INSTANCE);
        assertThat(holiday.descriptionPropertiesKey()).isEqualTo("easter.description");
        assertThat(holiday.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
        assertThat(holiday.validFrom()).isEqualTo(Year.of(2010));
        assertThat(holiday.validTo()).isEqualTo(Year.of(2020));
        assertThat(holiday.cycle()).isEqualTo(YearCycle.ODD_YEARS);

        final List<Movable.MovingCondition> conditions = holiday.conditions();
        assertThat(conditions).hasSize(1);
        assertThat(conditions.get(0).substitute()).isEqualTo(DayOfWeek.SATURDAY);
    }

    @Test
    void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
        final ChristianHoliday christianHoliday = new ChristianHoliday();
        christianHoliday.setType(de.focus_shift.jollyday.jackson.mapping.ChristianHolidayType.GOOD_FRIDAY);

        final JacksonChristianHoliday jacksonChristianHoliday = new JacksonChristianHoliday(christianHoliday);
        assertThat(jacksonChristianHoliday.type()).isEqualTo(ChristianHolidayType.GOOD_FRIDAY);
        assertThat(jacksonChristianHoliday.chronology()).isEqualTo(IsoChronology.INSTANCE);
        assertThat(jacksonChristianHoliday.descriptionPropertiesKey()).isEqualTo("christian.GOOD_FRIDAY");
        assertThat(jacksonChristianHoliday.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
        assertThat(jacksonChristianHoliday.validFrom()).isNull();
        assertThat(jacksonChristianHoliday.validTo()).isNull();
        assertThat(jacksonChristianHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
        assertThat(jacksonChristianHoliday.conditions()).isEmpty();
    }
}
