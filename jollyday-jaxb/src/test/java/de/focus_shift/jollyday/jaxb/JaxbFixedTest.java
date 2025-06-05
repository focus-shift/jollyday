package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Limited.YearCycle;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

class JaxbFixedTest {

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

    final JaxbFixed jaxbFixed = new JaxbFixed(fixed);
    assertThat(jaxbFixed.day()).isEqualTo(MonthDay.of(DECEMBER, 25));
    assertThat(jaxbFixed.cycle()).isEqualTo(YearCycle.ODD_YEARS);
    assertThat(jaxbFixed.descriptionPropertiesKey()).isEqualTo("fixed.description");
    assertThat(jaxbFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jaxbFixed.validFrom()).isEqualTo(Year.of(2010));
    assertThat(jaxbFixed.validTo()).isEqualTo(Year.of(2020));
    assertThat(jaxbFixed.conditions()).isEmpty();
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {
    final Fixed fixed = new Fixed();
    fixed.setDay(1);
    fixed.setMonth(Month.JANUARY);

    final JaxbFixed jaxbFixed = new JaxbFixed(fixed);
    assertThat(jaxbFixed.day()).isEqualTo(MonthDay.of(JANUARY, 1));
    assertThat(jaxbFixed.cycle()).isEqualTo(YearCycle.EVERY_YEAR);
    assertThat(jaxbFixed.descriptionPropertiesKey()).isNull();
    assertThat(jaxbFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jaxbFixed.validFrom()).isNull();
    assertThat(jaxbFixed.validTo()).isNull();
    assertThat(jaxbFixed.conditions()).isEmpty();
  }
}
