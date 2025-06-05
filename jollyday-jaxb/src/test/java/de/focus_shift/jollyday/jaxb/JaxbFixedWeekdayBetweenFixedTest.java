package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

class JaxbFixedWeekdayBetweenFixedTest {

  @Test
  void ensureFieldsAreSetAndMappedCorrectly() {
    final Fixed from = new Fixed();
    from.setDay(1);
    from.setMonth(Month.JANUARY);

    final Fixed to = new Fixed();
    to.setDay(10);
    to.setMonth(Month.JANUARY);

    final FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed = new FixedWeekdayBetweenFixed();
    fixedWeekdayBetweenFixed.setFrom(from);
    fixedWeekdayBetweenFixed.setTo(to);
    fixedWeekdayBetweenFixed.setWeekday(Weekday.MONDAY);
    fixedWeekdayBetweenFixed.setEvery(HolidayCycleType.ODD_YEARS);
    fixedWeekdayBetweenFixed.setDescriptionPropertiesKey("between.description");
    fixedWeekdayBetweenFixed.setLocalizedType(HolidayType.BANK_HOLIDAY);
    fixedWeekdayBetweenFixed.setValidFrom(2010);
    fixedWeekdayBetweenFixed.setValidTo(2020);

    final JaxbFixedWeekdayBetweenFixed jaxbFixedWeekdayBetweenFixed = new JaxbFixedWeekdayBetweenFixed(fixedWeekdayBetweenFixed);
    assertThat(jaxbFixedWeekdayBetweenFixed.from().day()).isEqualTo(MonthDay.of(JANUARY, 1));
    assertThat(jaxbFixedWeekdayBetweenFixed.to().day()).isEqualTo(MonthDay.of(JANUARY, 10));
    assertThat(jaxbFixedWeekdayBetweenFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jaxbFixedWeekdayBetweenFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jaxbFixedWeekdayBetweenFixed.descriptionPropertiesKey()).isEqualTo("between.description");
    assertThat(jaxbFixedWeekdayBetweenFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jaxbFixedWeekdayBetweenFixed.validFrom()).isEqualTo(Year.of(2010));
    assertThat(jaxbFixedWeekdayBetweenFixed.validTo()).isEqualTo(Year.of(2020));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final Fixed from = new Fixed();
    from.setDay(1);
    from.setMonth(Month.JANUARY);

    final Fixed to = new Fixed();
    to.setDay(10);
    to.setMonth(Month.JANUARY);

    final FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed = new FixedWeekdayBetweenFixed();
    fixedWeekdayBetweenFixed.setFrom(from);
    fixedWeekdayBetweenFixed.setTo(to);
    fixedWeekdayBetweenFixed.setWeekday(Weekday.MONDAY);

    final JaxbFixedWeekdayBetweenFixed jaxbFixedWeekdayBetweenFixed = new JaxbFixedWeekdayBetweenFixed(fixedWeekdayBetweenFixed);
    assertThat(jaxbFixedWeekdayBetweenFixed.from().day()).isEqualTo(MonthDay.of(JANUARY, 1));
    assertThat(jaxbFixedWeekdayBetweenFixed.to().day()).isEqualTo(MonthDay.of(JANUARY, 10));
    assertThat(jaxbFixedWeekdayBetweenFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jaxbFixedWeekdayBetweenFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jaxbFixedWeekdayBetweenFixed.descriptionPropertiesKey()).isNull();
    assertThat(jaxbFixedWeekdayBetweenFixed.holidayType()).isEqualTo(PUBLIC_HOLIDAY);
    assertThat(jaxbFixedWeekdayBetweenFixed.validFrom()).isNull();
    assertThat(jaxbFixedWeekdayBetweenFixed.validTo()).isNull();
  }
}
