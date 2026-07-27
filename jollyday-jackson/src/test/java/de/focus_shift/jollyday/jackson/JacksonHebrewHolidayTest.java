package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration.HebrewHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.HebrewHoliday;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonHebrewHolidayTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final HebrewHoliday hebrewHoliday = new HebrewHoliday();
    hebrewHoliday.setType(de.focus_shift.jollyday.jackson.mapping.HebrewHolidayType.ROSH_HASHANA);
    hebrewHoliday.setEvery(HolidayCycleType.ODD_YEARS);
    hebrewHoliday.setDescriptionPropertiesKey("hebrew.description");
    hebrewHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
    hebrewHoliday.setValidFrom(2010);
    hebrewHoliday.setValidTo(2020);

    final JacksonHebrewHoliday jacksonHebrewHoliday = new JacksonHebrewHoliday(hebrewHoliday);
    assertThat(jacksonHebrewHoliday.type()).isEqualTo(HebrewHolidayType.ROSH_HASHANA);
    assertThat(jacksonHebrewHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonHebrewHoliday.descriptionPropertiesKey()).isEqualTo("hebrew.description");
    assertThat(jacksonHebrewHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jacksonHebrewHoliday.validFrom()).hasValue(Year.of(2010));
    assertThat(jacksonHebrewHoliday.validTo()).hasValue(Year.of(2020));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final HebrewHoliday hebrewHoliday = new HebrewHoliday();
    hebrewHoliday.setType(de.focus_shift.jollyday.jackson.mapping.HebrewHolidayType.ROSH_HASHANA);

    final JacksonHebrewHoliday jacksonHebrewHoliday = new JacksonHebrewHoliday(hebrewHoliday);
    assertThat(jacksonHebrewHoliday.type()).isEqualTo(HebrewHolidayType.ROSH_HASHANA);
    assertThat(jacksonHebrewHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jacksonHebrewHoliday.descriptionPropertiesKey()).isEqualTo("hebrew.ROSH_HASHANA");
    assertThat(jacksonHebrewHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonHebrewHoliday.validFrom()).isEmpty();
    assertThat(jacksonHebrewHoliday.validTo()).isEmpty();
  }
}
