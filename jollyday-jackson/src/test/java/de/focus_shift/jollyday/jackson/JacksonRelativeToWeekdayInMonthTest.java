package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.When;
import de.focus_shift.jollyday.jackson.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonRelativeToWeekdayInMonthTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
    fixedWeekdayInMonth.setMonth(Month.MARCH);
    fixedWeekdayInMonth.setWhich(Which.FIRST);
    fixedWeekdayInMonth.setWeekday(Weekday.FRIDAY);

    final RelativeToWeekdayInMonth relativeToWeekdayInMonth = new RelativeToWeekdayInMonth();
    relativeToWeekdayInMonth.setFixedWeekday(fixedWeekdayInMonth);
    relativeToWeekdayInMonth.setWeekday(Weekday.MONDAY);
    relativeToWeekdayInMonth.setWhen(When.BEFORE);
    relativeToWeekdayInMonth.setDescriptionPropertiesKey("weekdayinmonth.description");
    relativeToWeekdayInMonth.setLocalizedType(de.focus_shift.jollyday.jackson.mapping.HolidayType.BANK_HOLIDAY);
    relativeToWeekdayInMonth.setValidFrom(2010);
    relativeToWeekdayInMonth.setValidTo(2020);
    relativeToWeekdayInMonth.setEvery(HolidayCycleType.ODD_YEARS);

    final JacksonRelativeToWeekdayInMonth jacksonRelativeToWeekdayInMonth = new JacksonRelativeToWeekdayInMonth(relativeToWeekdayInMonth);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().which()).isEqualTo(Occurrence.FIRST);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().weekday()).isEqualTo(DayOfWeek.FRIDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().month()).isEqualTo(java.time.Month.MARCH);
    assertThat(jacksonRelativeToWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.when()).isEqualTo(Relation.BEFORE);
    assertThat(jacksonRelativeToWeekdayInMonth.descriptionPropertiesKey()).isEqualTo("weekdayinmonth.description");
    assertThat(jacksonRelativeToWeekdayInMonth.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.validFrom()).isEqualTo(Year.of(2010));
    assertThat(jacksonRelativeToWeekdayInMonth.validTo()).isEqualTo(Year.of(2020));
    assertThat(jacksonRelativeToWeekdayInMonth.cycle()).isEqualTo(YearCycle.ODD_YEARS);
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth();
    fixedWeekdayInMonth.setMonth(Month.APRIL);
    fixedWeekdayInMonth.setWhich(Which.FIRST);
    fixedWeekdayInMonth.setWeekday(Weekday.SUNDAY);

    final RelativeToWeekdayInMonth relativeToWeekdayInMonth = new RelativeToWeekdayInMonth();
    relativeToWeekdayInMonth.setFixedWeekday(fixedWeekdayInMonth);
    relativeToWeekdayInMonth.setWeekday(Weekday.MONDAY);
    relativeToWeekdayInMonth.setWhen(When.AFTER);

    final JacksonRelativeToWeekdayInMonth jacksonRelativeToWeekdayInMonth = new JacksonRelativeToWeekdayInMonth(relativeToWeekdayInMonth);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().which()).isEqualTo(Occurrence.FIRST);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().weekday()).isEqualTo(DayOfWeek.SUNDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.weekdayInMonth().month()).isEqualTo(java.time.Month.APRIL);
    assertThat(jacksonRelativeToWeekdayInMonth.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.when()).isEqualTo(Relation.AFTER);
    assertThat(jacksonRelativeToWeekdayInMonth.descriptionPropertiesKey()).isNull();
    assertThat(jacksonRelativeToWeekdayInMonth.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonRelativeToWeekdayInMonth.validFrom()).isNull();
    assertThat(jacksonRelativeToWeekdayInMonth.validTo()).isNull();
    assertThat(jacksonRelativeToWeekdayInMonth.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
  }
}
