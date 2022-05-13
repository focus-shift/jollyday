package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.Fixed;
import de.focus_shift.jaxb.mapping.FixedWeekdayRelativeToFixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.jaxb.mapping.When;
import de.focus_shift.jaxb.mapping.Which;
import de.focus_shift.parser.impl.FixedWeekdayRelativeToFixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
class FixedWeekdayRelativeToFixedParserTest {

  private FixedWeekdayRelativeToFixedParser fwrtf = new FixedWeekdayRelativeToFixedParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    fwrtf.parse(2011, result, config);
    assertTrue(result.isEmpty(), "Result is not empty.");
  }

  @Test
  void testInvalid() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);
    Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    rule.setValidTo(2010);
    fwrtf.parse(2011, result, config);
    assertTrue(result.isEmpty(), "Result is not empty.");
  }

  @Test
  void testParserFirstBefore() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);
    Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2011, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2011, 1, 24), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testParserSecondBefore() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.SECOND);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);
    Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2011, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2011, 1, 17), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testParserThirdAfter() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.THIRD);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.AFTER);
    Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2011, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2011, 2, 14), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testParserFourthAfter() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FOURTH);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.AFTER);
    Fixed fixed = new Fixed();
    fixed.setDay(15);
    fixed.setMonth(Month.MARCH);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2011, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2011, 4, 12), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testParserFirstClosest() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.CLOSEST);
    Fixed fixed = new Fixed();
    fixed.setDay(14);
    fixed.setMonth(Month.JUNE);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2019, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2019, 6, 11), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testParserSecondClosest() {
    Set<Holiday> result = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    // WHICH is ignored for closest
    rule.setWhich(Which.SECOND);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.CLOSEST);
    Fixed fixed = new Fixed();
    fixed.setDay(14);
    fixed.setMonth(Month.JUNE);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    fwrtf.parse(2019, result, config);
    assertEquals(1, result.size(), "Wrong number of dates.");
    assertEquals(calendarUtil.create(2019, 6, 11), result.iterator().next().getDate(), "Wrong date.");
  }

  @Test
  void testClosestAdjusterSameMonth() {
    TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.MONDAY);
    LocalDate date = calendarUtil.create(2018, 12, 1); // Saturday
    LocalDate expectedDate = calendarUtil.create(2018, 12, 3); // Monday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterPreviousMonth() {
    TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.FRIDAY);
    LocalDate date = calendarUtil.create(2019, 6, 3); // Monday
    LocalDate expectedDate = calendarUtil.create(2019, 5, 31); // Friday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterNextMonth() {
    TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.MONDAY);
    LocalDate date = calendarUtil.create(2019, 6, 30); // Sunday
    LocalDate expectedDate = calendarUtil.create(2019, 7, 1); // Monday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterNextYear() {
    TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.WEDNESDAY);
    LocalDate date = calendarUtil.create(2019, 12, 31); // Tuesday
    LocalDate expectedDate = calendarUtil.create(2020, 1, 1); // Wednesday
    assertEquals(expectedDate, date.with(adjuster));
  }
}
