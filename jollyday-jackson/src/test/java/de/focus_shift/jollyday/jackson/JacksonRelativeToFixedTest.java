package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.When;
import org.junit.jupiter.api.Test;
import org.threeten.extra.Days;

import java.time.MonthDay;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonRelativeToFixedTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final Fixed fixed = new Fixed();
    fixed.setDay(1);
    fixed.setMonth(Month.JANUARY);

    final RelativeToFixed relativeToFixed = new RelativeToFixed();
    relativeToFixed.setDate(fixed);
    relativeToFixed.setDays(3);
    relativeToFixed.setWhen(When.AFTER);
    relativeToFixed.setEvery(HolidayCycleType.ODD_YEARS);
    relativeToFixed.setDescriptionPropertiesKey("relative.fixed.description");
    relativeToFixed.setLocalizedType(de.focus_shift.jollyday.jackson.mapping.HolidayType.BANK_HOLIDAY);
    relativeToFixed.setValidFrom(2000);
    relativeToFixed.setValidTo(2020);

    final JacksonRelativeToFixed jacksonRelativeToFixed = new JacksonRelativeToFixed(relativeToFixed);
    assertThat(jacksonRelativeToFixed.date().day()).isEqualTo(MonthDay.of(java.time.Month.JANUARY, 1));
    assertThat(jacksonRelativeToFixed.days()).isEqualTo(Days.of(3));
    assertThat(jacksonRelativeToFixed.when()).isEqualTo(Relation.AFTER);
    assertThat(jacksonRelativeToFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonRelativeToFixed.descriptionPropertiesKey()).isEqualTo("relative.fixed.description");
    assertThat(jacksonRelativeToFixed.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
    assertThat(jacksonRelativeToFixed.validFrom()).isEqualTo(Year.of(2000));
    assertThat(jacksonRelativeToFixed.validTo()).isEqualTo(Year.of(2020));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final Fixed fixed = new Fixed();
    fixed.setDay(10);
    fixed.setMonth(Month.FEBRUARY);

    final RelativeToFixed relativeToFixed = new RelativeToFixed();
    relativeToFixed.setDate(fixed);

    final JacksonRelativeToFixed jacksonRelativeToFixed = new JacksonRelativeToFixed(relativeToFixed);
    assertThat(jacksonRelativeToFixed.date().day()).isEqualTo(MonthDay.of(java.time.Month.FEBRUARY, 10));
    assertThat(jacksonRelativeToFixed.days()).isNull();
    assertThat(jacksonRelativeToFixed.when()).isNull();
    assertThat(jacksonRelativeToFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jacksonRelativeToFixed.descriptionPropertiesKey()).isNull();
    assertThat(jacksonRelativeToFixed.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonRelativeToFixed.validFrom()).isNull();
    assertThat(jacksonRelativeToFixed.validTo()).isNull();
  }
}
