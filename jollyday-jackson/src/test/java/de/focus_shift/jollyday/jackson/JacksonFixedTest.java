package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.MovingCondition;
import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;
import java.util.Collections;
import java.util.List;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

class JacksonFixedTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final Fixed fixed = new Fixed();
    fixed.setDay(25);
    fixed.setMonth(Month.DECEMBER);
    fixed.setEvery(HolidayCycleType.ODD_YEARS);
    fixed.setDescriptionPropertiesKey("fixed.description");
    fixed.setLocalizedType(HolidayType.BANK_HOLIDAY);
    fixed.setValidFrom(2010);
    fixed.setValidTo(2020);
    final MovingCondition movingCondition = new MovingCondition();
    fixed.setMovingCondition(List.of(movingCondition));

    final JacksonFixed jacksonFixed = new JacksonFixed(fixed);
    assertThat(jacksonFixed.day()).isEqualTo(MonthDay.of(DECEMBER, 25));
    assertThat(jacksonFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jacksonFixed.descriptionPropertiesKey()).isEqualTo("fixed.description");
    assertThat(jacksonFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jacksonFixed.validFrom()).isEqualTo(Year.of(2010));
    assertThat(jacksonFixed.validTo()).isEqualTo(Year.of(2020));
    assertThat(jacksonFixed.conditions()).hasSize(1);
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final Fixed fixed = new Fixed();
    fixed.setDay(1);
    fixed.setMonth(Month.JANUARY);
    fixed.setMovingCondition(Collections.emptyList());

    final JacksonFixed jacksonFixed = new JacksonFixed(fixed);
    assertThat(jacksonFixed.day()).isEqualTo(MonthDay.of(JANUARY, 1));
    assertThat(jacksonFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jacksonFixed.descriptionPropertiesKey()).isNull();
    assertThat(jacksonFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonFixed.validFrom()).isNull();
    assertThat(jacksonFixed.validTo()).isNull();
    assertThat(jacksonFixed.conditions()).isEmpty();
  }
}
