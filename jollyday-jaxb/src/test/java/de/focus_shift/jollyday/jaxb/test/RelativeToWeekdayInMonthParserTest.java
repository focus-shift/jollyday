package de.focus_shift.jollyday.jaxb.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.RelativeToWeekdayInMonthParser;
import de.focus_shift.jollyday.jaxb.JaxbHolidays;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.When;
import de.focus_shift.jollyday.jaxb.mapping.Which;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeToWeekdayInMonthParserTest {

  private final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(Year.of(2011), new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();

    final RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.AFTER);

    final FixedWeekdayInMonth date = new FixedWeekdayInMonth();
    date.setWhich(Which.SECOND);
    date.setWeekday(Weekday.MONDAY);
    date.setMonth(Month.JULY);
    rule.setFixedWeekday(date);
    config.getRelativeToWeekdayInMonth().add(rule);
    rule.setValidFrom(2012);

    final List<Holiday> holidays = sut.parse(Year.of(2011), new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testTueAfter2ndMondayJuly() {
    final Holidays config = new Holidays();

    final RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.AFTER);

    final FixedWeekdayInMonth date = new FixedWeekdayInMonth();
    date.setWhich(Which.SECOND);
    date.setWeekday(Weekday.MONDAY);
    date.setMonth(Month.JULY);
    rule.setFixedWeekday(date);
    config.getRelativeToWeekdayInMonth().add(rule);

    final List<Holiday> holidays = sut.parse(Year.of(2011), new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(LocalDate.of(2011, 7, 12));
  }

  @Test
  void testMonAfter4thMondayOctober() {
    final Holidays config = new Holidays();

    final RelativeToWeekdayInMonth rule = new RelativeToWeekdayInMonth();
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.AFTER);

    final FixedWeekdayInMonth date = new FixedWeekdayInMonth();
    date.setWhich(Which.FOURTH);
    date.setWeekday(Weekday.MONDAY);
    date.setMonth(Month.OCTOBER);
    rule.setFixedWeekday(date);
    config.getRelativeToWeekdayInMonth().add(rule);

    final List<Holiday> holidays = sut.parse(Year.of(2018), new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(LocalDate.of(2018, 10, 29));
  }
}
