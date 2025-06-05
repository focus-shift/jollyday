package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonFixedWeekdayInMonthTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
    fixedWeekdayInMonth.setWeekday(Weekday.MONDAY);
    fixedWeekdayInMonth.setMonth(Month.MARCH);
    fixedWeekdayInMonth.setWhich(Which.FIRST);
    fixedWeekdayInMonth.setEvery(HolidayCycleType.ODD_YEARS);
    fixedWeekdayInMonth.setDescriptionPropertiesKey("weekdayinmonth.description");
    fixedWeekdayInMonth.setLocalizedType(HolidayType.BANK_HOLIDAY);
    fixedWeekdayInMonth.setValidFrom(2012);
    fixedWeekdayInMonth.setValidTo(2022);

    final JacksonFixedWeekdayInMonth jacksonFixedWeekdayInMonth = new JacksonFixedWeekdayInMonth(fixedWeekdayInMonth);
    assertThat(jacksonFixedWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jacksonFixedWeekdayInMonth.month()).isEqualTo(java.time.Month.MARCH);
    assertThat(jacksonFixedWeekdayInMonth.which()).isEqualTo(Occurrence.FIRST);
    assertThat(jacksonFixedWeekdayInMonth.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonFixedWeekdayInMonth.descriptionPropertiesKey()).isEqualTo("weekdayinmonth.description");
    assertThat(jacksonFixedWeekdayInMonth.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jacksonFixedWeekdayInMonth.validFrom()).isEqualTo(Year.of(2012));
    assertThat(jacksonFixedWeekdayInMonth.validTo()).isEqualTo(Year.of(2022));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final FixedWeekdayInMonth mapping = new FixedWeekdayInMonth();
    mapping.setWeekday(Weekday.FRIDAY);
    mapping.setMonth(Month.JUNE);
    mapping.setWhich(Which.LAST);

    final JacksonFixedWeekdayInMonth holiday = new JacksonFixedWeekdayInMonth(mapping);
    assertThat(holiday.weekday()).isEqualTo(DayOfWeek.FRIDAY);
    assertThat(holiday.month()).isEqualTo(java.time.Month.JUNE);
    assertThat(holiday.which()).isEqualTo(Occurrence.LAST);
    assertThat(holiday.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(holiday.descriptionPropertiesKey()).isNull();
    assertThat(holiday.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(holiday.validFrom()).isNull();
    assertThat(holiday.validTo()).isNull();
  }
}
