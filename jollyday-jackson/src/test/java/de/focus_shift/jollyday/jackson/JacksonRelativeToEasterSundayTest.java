package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.IsoChronology;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonRelativeToEasterSundayTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final RelativeToEasterSunday relativeToEasterSunday = new RelativeToEasterSunday();
    relativeToEasterSunday.setDays(10);
    relativeToEasterSunday.setChronology(ChronologyType.JULIAN);
    relativeToEasterSunday.setEvery(HolidayCycleType.ODD_YEARS);
    relativeToEasterSunday.setDescriptionPropertiesKey("easter.description");
    relativeToEasterSunday.setLocalizedType(de.focus_shift.jollyday.jackson.mapping.HolidayType.BANK_HOLIDAY);
    relativeToEasterSunday.setValidFrom(2015);
    relativeToEasterSunday.setValidTo(2025);

    final JacksonRelativeToEasterSunday jacksonRelativeToEasterSunday = new JacksonRelativeToEasterSunday(relativeToEasterSunday);
    assertThat(jacksonRelativeToEasterSunday.days()).isEqualTo(Days.of(10));
    assertThat(jacksonRelativeToEasterSunday.chronology()).isEqualTo(JulianChronology.INSTANCE);
    assertThat(jacksonRelativeToEasterSunday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonRelativeToEasterSunday.descriptionPropertiesKey()).isEqualTo("easter.description");
    assertThat(jacksonRelativeToEasterSunday.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
    assertThat(jacksonRelativeToEasterSunday.validFrom()).isEqualTo(Year.of(2015));
    assertThat(jacksonRelativeToEasterSunday.validTo()).isEqualTo(Year.of(2025));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final RelativeToEasterSunday relativeToEasterSunday = new RelativeToEasterSunday();
    relativeToEasterSunday.setDays(-2);

    final JacksonRelativeToEasterSunday jacksonRelativeToEasterSunday = new JacksonRelativeToEasterSunday(relativeToEasterSunday);
    assertThat(jacksonRelativeToEasterSunday.days()).isEqualTo(Days.of(-2));
    assertThat(jacksonRelativeToEasterSunday.chronology()).isEqualTo(IsoChronology.INSTANCE);
    assertThat(jacksonRelativeToEasterSunday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jacksonRelativeToEasterSunday.descriptionPropertiesKey()).isNull();
    assertThat(jacksonRelativeToEasterSunday.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonRelativeToEasterSunday.validFrom()).isNull();
    assertThat(jacksonRelativeToEasterSunday.validTo()).isNull();
  }
}
