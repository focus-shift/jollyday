package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jackson.mapping.HolidayType;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.When;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonChristianHolidayTest {

  @Test
  void ensureDateIsSetAndMappedCorrectly() {

    final Fixed firstJanuary = new Fixed();
    firstJanuary.setDay(1);
    firstJanuary.setMonth(Month.JANUARY);

    final RelativeToFixed relativeToFixed = new RelativeToFixed();
    relativeToFixed.setWeekday(Weekday.MONDAY);
    relativeToFixed.setWhen(When.AFTER);
    relativeToFixed.setDate(firstJanuary);
    relativeToFixed.setEvery(HolidayCycleType.EVEN_YEARS);
    relativeToFixed.setDescriptionPropertiesKey("description");
    relativeToFixed.setLocalizedType(HolidayType.BANK_HOLIDAY);
    relativeToFixed.setValidFrom(2000);
    relativeToFixed.setValidTo(2001);
    final JacksonRelativeToFixed jacksonRelativeToFixed = new JacksonRelativeToFixed(relativeToFixed);

    assertThat(jacksonRelativeToFixed.date().day()).isEqualTo(new JacksonFixed(firstJanuary).day());
    assertThat(jacksonRelativeToFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jacksonRelativeToFixed.when()).isEqualTo(Relation.AFTER);
    assertThat(jacksonRelativeToFixed.cycle()).isEqualTo(Limited.YearCycle.EVEN_YEARS);
    assertThat(jacksonRelativeToFixed.descriptionPropertiesKey()).isEqualTo("description");
    assertThat(jacksonRelativeToFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jacksonRelativeToFixed.validFrom()).isEqualTo(Year.of(2000));
    assertThat(jacksonRelativeToFixed.validTo()).isEqualTo(Year.of(2001));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {

    final Fixed firstJanuary = new Fixed();
    firstJanuary.setDay(1);
    firstJanuary.setMonth(Month.JANUARY);

    final RelativeToFixed relativeToFixed = new RelativeToFixed();
    relativeToFixed.setDate(firstJanuary);
    final JacksonRelativeToFixed jacksonRelativeToFixed = new JacksonRelativeToFixed(relativeToFixed);

    assertThat(jacksonRelativeToFixed.date().day()).isEqualTo(new JacksonFixed(firstJanuary).day());
    assertThat(jacksonRelativeToFixed.weekday()).isNull();
    assertThat(jacksonRelativeToFixed.when()).isNull();
    assertThat(jacksonRelativeToFixed.cycle()).isEqualTo(Limited.YearCycle.EVERY_YEAR);
    assertThat(jacksonRelativeToFixed.descriptionPropertiesKey()).isNull();
    assertThat(jacksonRelativeToFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jacksonRelativeToFixed.validFrom()).isNull();
    assertThat(jacksonRelativeToFixed.validTo()).isNull();
  }
}
