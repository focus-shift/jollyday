package de.focus_shift.jollyday.jaxb.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.FixedParser;
import de.focus_shift.jollyday.jaxb.JaxbHolidays;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.HolidayCycleType;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;
import de.focus_shift.jollyday.jaxb.mapping.Month;
import de.focus_shift.jollyday.jaxb.mapping.MovingCondition;
import de.focus_shift.jollyday.jaxb.mapping.Weekday;
import de.focus_shift.jollyday.jaxb.mapping.With;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.focus_shift.jollyday.jaxb.mapping.Month.JANUARY;
import static de.focus_shift.jollyday.jaxb.mapping.Month.MARCH;
import static de.focus_shift.jollyday.jaxb.mapping.Month.MAY;
import static de.focus_shift.jollyday.jaxb.mapping.Weekday.FRIDAY;
import static de.focus_shift.jollyday.jaxb.mapping.Weekday.MONDAY;
import static de.focus_shift.jollyday.jaxb.mapping.Weekday.SATURDAY;
import static de.focus_shift.jollyday.jaxb.mapping.Weekday.SUNDAY;
import static de.focus_shift.jollyday.jaxb.mapping.With.NEXT;
import static de.focus_shift.jollyday.jaxb.mapping.With.PREVIOUS;
import static org.assertj.core.api.Assertions.assertThat;


class FixedParserTest {

  private final FixedParser sut = new FixedParser();

  @Test
  void testFixedWithValidity() {
    final Holidays config = createHolidays(
      createFixed(1, JANUARY),
      createFixed(3, MARCH),
      createFixed(5, MAY, 2011, null)
    );

    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    containsAll(holidays, LocalDate.of(2010, 1, 1), LocalDate.of(2010, 3, 3));
  }

  @Test
  void testFixedWithMoving() {
    final Holidays config = createHolidays(
      createFixed(8, JANUARY, createMoving(SATURDAY, PREVIOUS, FRIDAY)),
      createFixed(23, JANUARY, createMoving(SUNDAY, NEXT, MONDAY))
    );

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    containsAll(holidays, LocalDate.of(2011, 1, 7), LocalDate.of(2011, 1, 24));
  }

  @Test
  void testCycle2YearsInvalidRepeat() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidFrom(2010);
    fixed.setEvery(HolidayCycleType.TWO_YEARS);
    final Holidays config = createHolidays(fixed);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testCycle2YearsReverseInvalidRepeat() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidTo(2010);
    fixed.setEvery(HolidayCycleType.TWO_YEARS);
    final Holidays config = createHolidays(fixed);

    final List<Holiday> holidays = sut.parse(2009, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testCycle3Years() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidFrom(2010);
    fixed.setEvery(HolidayCycleType.THREE_YEARS);
    final Holidays config = createHolidays(fixed);
    final List<Holiday> holidays = sut.parse(2013, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
  }

  @Test
  void testCycle3YearsReverse() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidTo(2010);
    fixed.setEvery(HolidayCycleType.THREE_YEARS);
    final Holidays config = createHolidays(fixed);
    final List<Holiday> holidays = sut.parse(2007, new JaxbHolidays(config));
    assertThat(holidays).hasSize(1);
  }

  @Test
  void testCycle5YearsInvalidBeforeStart() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidFrom(2010);
    fixed.setEvery(HolidayCycleType.TWO_YEARS);
    final Holidays config = createHolidays(fixed);

    final List<Holiday> holidays = sut.parse(2005, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testCycle5YearsReverseInvalidAfterEnd() {
    final Fixed fixed = createFixed(4, JANUARY);
    fixed.setValidTo(2010);
    fixed.setEvery(HolidayCycleType.FIVE_YEARS);
    final Holidays config = createHolidays(fixed);

    final List<Holiday> holidays = sut.parse(2015, new JaxbHolidays(config));
    assertThat(holidays).isEmpty();
  }

  private void containsAll(List<Holiday> list, LocalDate... dates) {
    assertThat(list).hasSameSizeAs(dates);
    final List<LocalDate> expected = new ArrayList<>(Arrays.asList(dates));
    Collections.sort(expected);
    Collections.sort(list);
    for (int i = 0; i < expected.size(); i++) {
      assertThat(list.get(i).getDate()).isEqualTo(expected.get(i));
    }
  }

  private Holidays createHolidays(Fixed... fs) {
    final Holidays config = new Holidays();
    config.getFixed().addAll(List.of(fs));
    return config;
  }

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
