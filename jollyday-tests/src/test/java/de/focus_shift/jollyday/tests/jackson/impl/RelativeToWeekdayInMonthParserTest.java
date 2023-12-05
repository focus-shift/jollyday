package de.focus_shift.jollyday.tests.jackson.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.RelativeToWeekdayInMonthParser;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.When;
import de.focus_shift.jollyday.jackson.mapping.Which;

/**
 * @author svdi1de
 */
class RelativeToWeekdayInMonthParserTest {

  private final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
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

    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
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

    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2011, 7, 12));
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

    final List<Holiday> holidays = sut.parse(2018, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2018, 10, 29));
  }
}
