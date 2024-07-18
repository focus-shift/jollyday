package de.focus_shift.jollyday.jaxb.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.RelativeToFixedParser;
import de.focus_shift.jollyday.jaxb.JaxbHolidays;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeToFixedParserTest {

  private final RelativeToFixedParser sut = new RelativeToFixedParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(Year.of(2010), new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final RelativeToFixed rule = new RelativeToFixed();
    rule.setValidFrom(2011);
    config.getRelativeToFixed().add(rule);
    final List<Holiday> holidays = sut.parse(Year.of(2010), new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testWeekday() {
    final Holidays config = new Holidays();
    final RelativeToFixed rule = new RelativeToFixed();
    rule.setWeekday(Weekday.THURSDAY);
    rule.setWhen(When.AFTER);

    final Fixed date = new Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(Year.of(2011), new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(LocalDate.of(2011, 8, 11));
  }

  @Test
  void testSameWeekday() {
    final Holidays config = new Holidays();

    final RelativeToFixed rule = new RelativeToFixed();
    rule.setWeekday(Weekday.WEDNESDAY);
    rule.setWhen(When.BEFORE);

    final Fixed date = new Fixed();
    date.setDay(23);
    date.setMonth(Month.NOVEMBER);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(Year.of(2016), new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(LocalDate.of(2016, 11, 16));
  }

  @Test
  void testNumberOfDays() {
    final Holidays config = new Holidays();

    final RelativeToFixed rule = new RelativeToFixed();
    rule.setDays(3);
    rule.setWhen(When.BEFORE);

    final Fixed date = new Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(Year.of(2011), new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(LocalDate.of(2011, 8, 2));
  }
}
