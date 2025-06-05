package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.HolidayType;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class JaxbChristianHolidayTest {

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
    final JaxbRelativeToFixed jaxbRelativeToFixed = new JaxbRelativeToFixed(relativeToFixed);

    assertThat(jaxbRelativeToFixed.date().day()).isEqualTo(new JaxbFixed(firstJanuary).day());
    assertThat(jaxbRelativeToFixed.weekday()).isEqualTo(DayOfWeek.MONDAY);
    assertThat(jaxbRelativeToFixed.when()).isEqualTo(Relation.AFTER);
    assertThat(jaxbRelativeToFixed.cycle()).isEqualTo(Limited.YearCycle.EVEN_YEARS);
    assertThat(jaxbRelativeToFixed.descriptionPropertiesKey()).isEqualTo("description");
    assertThat(jaxbRelativeToFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY);
    assertThat(jaxbRelativeToFixed.validFrom()).isEqualTo(Year.of(2000));
    assertThat(jaxbRelativeToFixed.validTo()).isEqualTo(Year.of(2001));
  }

  @Test
  void ensureToReturnNullOrDefaultValuesOnNotSetValues() {

    final Fixed firstJanuary = new Fixed();
    firstJanuary.setDay(1);
    firstJanuary.setMonth(Month.JANUARY);

    final RelativeToFixed relativeToFixed = new RelativeToFixed();
    relativeToFixed.setDate(firstJanuary);
    final JaxbRelativeToFixed jaxbRelativeToFixed = new JaxbRelativeToFixed(relativeToFixed);

    assertThat(jaxbRelativeToFixed.date().day()).isEqualTo(new JaxbFixed(firstJanuary).day());
    assertThat(jaxbRelativeToFixed.weekday()).isNull();
    assertThat(jaxbRelativeToFixed.when()).isNull();
    assertThat(jaxbRelativeToFixed.cycle()).isEqualTo(Limited.YearCycle.EVERY_YEAR);
    assertThat(jaxbRelativeToFixed.descriptionPropertiesKey()).isNull();
    assertThat(jaxbRelativeToFixed.holidayType()).isEqualTo(de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY);
    assertThat(jaxbRelativeToFixed.validFrom()).isNull();
    assertThat(jaxbRelativeToFixed.validTo()).isNull();
  }
}
