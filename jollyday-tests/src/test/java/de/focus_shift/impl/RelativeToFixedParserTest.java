package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.Fixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.jaxb.mapping.When;
import de.focus_shift.parser.impl.RelativeToFixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelativeToFixedParserTest {

  private final RelativeToFixedParser sut = new RelativeToFixedParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final de.focus_shift.jaxb.mapping.RelativeToFixed rule = new de.focus_shift.jaxb.mapping.RelativeToFixed();
    rule.setValidFrom(2011);
    config.getRelativeToFixed().add(rule);
    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testWeekday() {
    final Holidays config = new Holidays();
    final de.focus_shift.jaxb.mapping.RelativeToFixed rule = new de.focus_shift.jaxb.mapping.RelativeToFixed();
    rule.setWeekday(Weekday.THURSDAY);
    rule.setWhen(When.AFTER);
    final de.focus_shift.jaxb.mapping.Fixed date = new de.focus_shift.jaxb.mapping.Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2011, 8, 11), holidays.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testSameWeekday() {
    final Holidays config = new Holidays();

    final de.focus_shift.jaxb.mapping.RelativeToFixed rule = new de.focus_shift.jaxb.mapping.RelativeToFixed();
    rule.setWeekday(Weekday.WEDNESDAY);
    rule.setWhen(When.BEFORE);

    final de.focus_shift.jaxb.mapping.Fixed date = new de.focus_shift.jaxb.mapping.Fixed();
    date.setDay(23);
    date.setMonth(Month.NOVEMBER);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2016, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2016, 11, 16), holidays.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testNumberOfDays() {
    final de.focus_shift.jaxb.mapping.Holidays config = new de.focus_shift.jaxb.mapping.Holidays();

    final de.focus_shift.jaxb.mapping.RelativeToFixed rule = new RelativeToFixed();
    rule.setDays(3);
    rule.setWhen(When.BEFORE);

    final de.focus_shift.jaxb.mapping.Fixed date = new Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2011, 8, 2), holidays.iterator().next().getDate(), "Wrong date.");
  }

}
