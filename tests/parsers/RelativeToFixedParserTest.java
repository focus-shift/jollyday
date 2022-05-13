package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.Fixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.jaxb.mapping.When;
import de.focus_shift.parser.impl.RelativeToFixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
class RelativeToFixedParserTest {

  private RelativeToFixedParser rtfp = new RelativeToFixedParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    rtfp.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testInvalid() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    RelativeToFixed rule = new RelativeToFixed();
    rule.setValidFrom(2011);
    config.getRelativeToFixed().add(rule);
    rtfp.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testWeekday() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    RelativeToFixed rule = new RelativeToFixed();
    rule.setWeekday(Weekday.THURSDAY);
    rule.setWhen(When.AFTER);
    Fixed date = new Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);
    rtfp.parse(2011, holidays, config);
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2011, 8, 11), holidays.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testSameWeekday() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    RelativeToFixed rule = new RelativeToFixed();
    rule.setWeekday(Weekday.WEDNESDAY);
    rule.setWhen(When.BEFORE);
    Fixed date = new Fixed();
    date.setDay(23);
    date.setMonth(Month.NOVEMBER);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);
    rtfp.parse(2016, holidays, config);
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2016, 11, 16), holidays.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testNumberOfDays() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    RelativeToFixed rule = new RelativeToFixed();
    rule.setDays(3);
    rule.setWhen(When.BEFORE);
    Fixed date = new Fixed();
    date.setDay(5);
    date.setMonth(Month.AUGUST);
    rule.setDate(date);
    config.getRelativeToFixed().add(rule);
    rtfp.parse(2011, holidays, config);
    assertEquals(1, holidays.size(), "Number of holidays wrong.");
    assertEquals(calendarUtil.create(2011, 8, 2), holidays.iterator().next().getDate(), "Wrong date.");
  }

}
