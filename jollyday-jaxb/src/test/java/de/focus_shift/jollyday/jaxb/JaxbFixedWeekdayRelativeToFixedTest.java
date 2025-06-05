package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import de.focus_shift.jollyday.jaxb.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.jaxb.mapping.HolidayType.BANK_HOLIDAY;
import static org.assertj.core.api.Assertions.assertThat;

class JaxbFixedWeekdayRelativeToFixedTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final Fixed fixed = new Fixed();
    fixed.setDay(15);
    fixed.setMonth(Month.APRIL);

    final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = new FixedWeekdayRelativeToFixed();
    fixedWeekdayRelativeToFixed.setDay(fixed);
    fixedWeekdayRelativeToFixed.setWeekday(Weekday.THURSDAY);
    fixedWeekdayRelativeToFixed.setWhen(When.BEFORE);
    fixedWeekdayRelativeToFixed.setWhich(Which.SECOND);
    fixedWeekdayRelativeToFixed.setEvery(HolidayCycleType.EVEN_YEARS);
    fixedWeekdayRelativeToFixed.setDescriptionPropertiesKey("relative.description");
    fixedWeekdayRelativeToFixed.setLocalizedType(BANK_HOLIDAY);
    fixedWeekdayRelativeToFixed.setValidFrom(2011);
    fixedWeekdayRelativeToFixed.setValidTo(2021);

    final JaxbFixedWeekdayRelativeToFixed jaxbFixedWeekdayRelativeToFixed = new JaxbFixedWeekdayRelativeToFixed(fixedWeekdayRelativeToFixed);
    assertThat(jaxbFixedWeekdayRelativeToFixed.day().day()).isEqualTo(MonthDay.of(java.time.Month.APRIL, 15));
    assertThat(jaxbFixedWeekdayRelativeToFixed.weekday()).isEqualTo(DayOfWeek.THURSDAY);
    assertThat(jaxbFixedWeekdayRelativeToFixed.when()).isEqualTo(Relation.BEFORE);
    assertThat(jaxbFixedWeekdayRelativeToFixed.which()).isEqualTo(Occurrence.SECOND);
    assertThat(jaxbFixedWeekdayRelativeToFixed.cycle()).isEqualTo(YearCycle.EVEN_YEARS);
    assertThat(jaxbFixedWeekdayRelativeToFixed.descriptionPropertiesKey()).isEqualTo("relative.description");
    assertThat(jaxbFixedWeekdayRelativeToFixed.holidayType()).isEqualTo(HolidayType.BANK_HOLIDAY);
    assertThat(jaxbFixedWeekdayRelativeToFixed.validFrom()).isEqualTo(Year.of(2011));
    assertThat(jaxbFixedWeekdayRelativeToFixed.validTo()).isEqualTo(Year.of(2021));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final Fixed fixed = new Fixed();
    fixed.setDay(5);
    fixed.setMonth(Month.MAY);

    final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = new FixedWeekdayRelativeToFixed();
    fixedWeekdayRelativeToFixed.setDay(fixed);
    fixedWeekdayRelativeToFixed.setWeekday(Weekday.MONDAY);
    fixedWeekdayRelativeToFixed.setWhen(When.AFTER);
    fixedWeekdayRelativeToFixed.setWhich(Which.FIRST);

    final JaxbFixedWeekdayRelativeToFixed jaxbFixedWeekdayRelativeToFixed = new JaxbFixedWeekdayRelativeToFixed(fixedWeekdayRelativeToFixed);
    assertThat(jaxbFixedWeekdayRelativeToFixed.day().day()).isEqualTo(MonthDay.of(java.time.Month.MAY, 5));
    assertThat(jaxbFixedWeekdayRelativeToFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jaxbFixedWeekdayRelativeToFixed.when()).isEqualTo(Relation.AFTER);
    assertThat(jaxbFixedWeekdayRelativeToFixed.which()).isEqualTo(Occurrence.FIRST);
    assertThat(jaxbFixedWeekdayRelativeToFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jaxbFixedWeekdayRelativeToFixed.descriptionPropertiesKey()).isNull();
    assertThat(jaxbFixedWeekdayRelativeToFixed.holidayType()).isEqualTo(HolidayType.PUBLIC_HOLIDAY);
    assertThat(jaxbFixedWeekdayRelativeToFixed.validFrom()).isNull();
    assertThat(jaxbFixedWeekdayRelativeToFixed.validTo()).isNull();
  }
}
