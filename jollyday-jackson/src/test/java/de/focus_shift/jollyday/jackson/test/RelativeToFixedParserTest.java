package de.focus_shift.jollyday.jackson.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.RelativeToFixedParser;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import de.focus_shift.jollyday.jackson.mapping.When;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeToFixedParserTest {

  private final RelativeToFixedParser sut = new RelativeToFixedParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final RelativeToFixed rule = new RelativeToFixed();
    rule.setValidFrom(2011);
    config.getRelativeToFixed().add(rule);
    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
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

    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(CalendarUtil.create(2011, 8, 11));
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

    final List<Holiday> holidays = sut.parse(2016, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(CalendarUtil.create(2016, 11, 16));
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

    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(CalendarUtil.create(2011, 8, 2));
  }
}
