package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.Fixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.MovingCondition;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.jaxb.mapping.With;
import de.focus_shift.parser.impl.FixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.focus_shift.jaxb.mapping.Month.JANUARY;
import static de.focus_shift.jaxb.mapping.Month.MARCH;
import static de.focus_shift.jaxb.mapping.Month.MAY;
import static de.focus_shift.jaxb.mapping.Weekday.FRIDAY;
import static de.focus_shift.jaxb.mapping.Weekday.MONDAY;
import static de.focus_shift.jaxb.mapping.Weekday.SATURDAY;
import static de.focus_shift.jaxb.mapping.Weekday.SUNDAY;
import static de.focus_shift.jaxb.mapping.With.NEXT;
import static de.focus_shift.jaxb.mapping.With.PREVIOUS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
class FixedParserTest {

  private final FixedParser sut = new FixedParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testFixedWithValidity() {
    final Holidays config = createHolidays(
      createFixed(1, JANUARY),
      createFixed(3, MARCH),
      createFixed(5, MAY, 2011, null)
    );

    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    containsAll(holidays, calendarUtil.create(2010, 1, 1), calendarUtil.create(2010, 3, 3));
  }

  @Test
  void testFixedWithMoving() {
    final Holidays config = createHolidays(
      createFixed(8, JANUARY, createMoving(SATURDAY, PREVIOUS, FRIDAY)),
      createFixed(23, JANUARY, createMoving(SUNDAY, NEXT, MONDAY))
    );

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    containsAll(holidays, calendarUtil.create(2011, 1, 7), calendarUtil.create(2011, 1, 24));
  }

  @Test
  void testCyle2YearsInvalid() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidFrom(2010);
    fixed.setEvery("TWO_YEARS");
    final Holidays config = createHolidays(fixed);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testCyle3Years() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidFrom(2010);
    fixed.setEvery("THREE_YEARS");
    final Holidays config = createHolidays(fixed);
    final List<Holiday> holidays = sut.parse(2013, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Wrong number of holidays.");
  }

  private void containsAll(List<Holiday> list, LocalDate... dates) {
    assertEquals(dates.length, list.size(), "Number of holidays.");
    List<LocalDate> expected = new ArrayList<>(Arrays.asList(dates));
    Collections.sort(expected);
    Collections.sort(list);
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), list.get(i).getDate(), "Missing date.");
    }
  }

  private Holidays createHolidays(Fixed... fs) {
    final Holidays config = new Holidays();
    config.getFixed().addAll(List.of(fs));
    return config;
  }

  /**
   * @return
   */
  Fixed createFixed(int day, Month m, MovingCondition... mc) {
    final Fixed fixed = new Fixed();
    fixed.setDay(day);
    fixed.setMonth(m);
    fixed.getMovingCondition().addAll(List.of(mc));
    return fixed;
  }

  Fixed createFixed(int day, Month month, Integer validFrom, Integer validUntil, MovingCondition... mc) {
    final Fixed fixed = createFixed(day, month, mc);
    fixed.setValidFrom(validFrom);
    fixed.setValidTo(validUntil);
    return fixed;
  }

  MovingCondition createMoving(Weekday substitute, With with, Weekday weekday) {
    final MovingCondition mc = new MovingCondition();
    mc.setSubstitute(substitute);
    mc.setWith(with);
    mc.setWeekday(weekday);
    return mc;
  }
}
