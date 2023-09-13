package de.focus_shift.tests.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FixedWeekdayRelativeToFixedParserTest {

  private final FixedWeekdayRelativeToFixedParser sut = new FixedWeekdayRelativeToFixedParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);

    final Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);
    rule.setValidTo(2010);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testParserFirstBefore() {
    final Holidays config = new Holidays();

    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);

    final Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2011, 1, 24));
  }

  @Test
  void testParserSecondBefore() {
    final Holidays config = new Holidays();
    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.SECOND);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.BEFORE);

    final Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2011, 1, 17));
  }

  @Test
  void testParserThirdAfter() {
    final Holidays config = new Holidays();

    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.THIRD);
    rule.setWeekday(Weekday.MONDAY);
    rule.setWhen(When.AFTER);

    final Fixed fixed = new Fixed();
    fixed.setDay(29);
    fixed.setMonth(Month.JANUARY);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2011, 2, 14));
  }

  @Test
  void testParserFourthAfter() {
    final Holidays config = new Holidays();

    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FOURTH);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.AFTER);

    final Fixed fixed = new Fixed();
    fixed.setDay(15);
    fixed.setMonth(Month.MARCH);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2011, 4, 12));
  }

  @Test
  void testParserFirstClosest() {
    final Holidays config = new Holidays();

    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    rule.setWhich(Which.FIRST);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.CLOSEST);

    final Fixed fixed = new Fixed();
    fixed.setDay(14);
    fixed.setMonth(Month.JUNE);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2019, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2019, 6, 11));
  }

  @Test
  void testParserSecondClosest() {
    final Holidays config = new Holidays();

    final FixedWeekdayRelativeToFixed rule = new FixedWeekdayRelativeToFixed();
    // WHICH is ignored for closest
    rule.setWhich(Which.SECOND);
    rule.setWeekday(Weekday.TUESDAY);
    rule.setWhen(When.CLOSEST);

    final Fixed fixed = new Fixed();
    fixed.setDay(14);
    fixed.setMonth(Month.JUNE);
    rule.setDay(fixed);
    config.getFixedWeekdayRelativeToFixed().add(rule);

    final List<Holiday> holidays = sut.parse(2019, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(calendarUtil.create(2019, 6, 11));
  }

/* TODO
  @Test
  void testClosestAdjusterSameMonth() {
    final TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.MONDAY);
    final LocalDate date = calendarUtil.create(2018, 12, 1); // Saturday
    final LocalDate expectedDate = calendarUtil.create(2018, 12, 3); // Monday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterPreviousMonth() {
    final TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.FRIDAY);
    final LocalDate date = calendarUtil.create(2019, 6, 3); // Monday
    final LocalDate expectedDate = calendarUtil.create(2019, 5, 31); // Friday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterNextMonth() {
    final TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.MONDAY);
    final LocalDate date = calendarUtil.create(2019, 6, 30); // Sunday
    final LocalDate expectedDate = calendarUtil.create(2019, 7, 1); // Monday
    assertEquals(expectedDate, date.with(adjuster));
  }

  @Test
  void testClosestAdjusterNextYear() {
    final TemporalAdjuster adjuster = FixedWeekdayRelativeToFixedParser.closest(DayOfWeek.WEDNESDAY);
    final LocalDate date = calendarUtil.create(2019, 12, 31); // Tuesday
    final LocalDate expectedDate = calendarUtil.create(2020, 1, 1); // Wednesday
    assertEquals(expectedDate, date.with(adjuster));
  }*/
}
