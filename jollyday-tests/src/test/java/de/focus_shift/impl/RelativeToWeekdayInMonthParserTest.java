package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.RelativeToWeekdayInMonth;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.jaxb.mapping.When;
import de.focus_shift.jaxb.mapping.Which;
import de.focus_shift.parser.impl.RelativeToWeekdayInMonthParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
class RelativeToWeekdayInMonthParserTest {

  private final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Result is not empty.");
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

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Result is not empty.");
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

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2011, 7, 12), holidays.iterator().next().getDate(), "Wrong date.");
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

    final List<Holiday> holidays = sut.parse(2018, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2018, 10, 29), holidays.iterator().next().getDate(), "Wrong date.");
  }

}
