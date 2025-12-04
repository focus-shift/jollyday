package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday.EthiopianOrthodoxHolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonEthiopianOrthodoxHolidayTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
    ethiopianOrthodoxHoliday.setType(de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType.TIMKAT);
    ethiopianOrthodoxHoliday.setEvery(HolidayCycleType.ODD_YEARS);
    ethiopianOrthodoxHoliday.setDescriptionPropertiesKey("ethiopian.description");
    ethiopianOrthodoxHoliday.setLocalizedType(HolidayType.BANK_HOLIDAY);
    ethiopianOrthodoxHoliday.setValidFrom(2010);
    ethiopianOrthodoxHoliday.setValidTo(2020);

    final JacksonEthiopianOrthodoxHoliday jacksonEthiopianOrthodoxHoliday = new JacksonEthiopianOrthodoxHoliday(ethiopianOrthodoxHoliday);
    assertThat(jacksonEthiopianOrthodoxHoliday.type()).isEqualTo(EthiopianOrthodoxHolidayType.TIMKAT);
    assertThat(jacksonEthiopianOrthodoxHoliday.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonEthiopianOrthodoxHoliday.descriptionPropertiesKey()).isEqualTo("ethiopian.description");
    assertThat(jacksonEthiopianOrthodoxHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jacksonEthiopianOrthodoxHoliday.validFrom()).isEqualTo(Year.of(2010));
    assertThat(jacksonEthiopianOrthodoxHoliday.validTo()).isEqualTo(Year.of(2020));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday = new EthiopianOrthodoxHoliday();
    ethiopianOrthodoxHoliday.setType(de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHolidayType.TIMKAT);

    final JacksonEthiopianOrthodoxHoliday jacksonEthiopianOrthodoxHoliday = new JacksonEthiopianOrthodoxHoliday(ethiopianOrthodoxHoliday);
    assertThat(jacksonEthiopianOrthodoxHoliday.type()).isEqualTo(EthiopianOrthodoxHolidayType.TIMKAT);
    assertThat(jacksonEthiopianOrthodoxHoliday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jacksonEthiopianOrthodoxHoliday.descriptionPropertiesKey()).isEqualTo("ethiopian.orthodox.TIMKAT");
    assertThat(jacksonEthiopianOrthodoxHoliday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonEthiopianOrthodoxHoliday.validFrom()).isNull();
    assertThat(jacksonEthiopianOrthodoxHoliday.validTo()).isNull();
  }
}
