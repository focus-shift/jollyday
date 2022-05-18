package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.parser.impl.FixedWeekdayBetweenFixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
class FixedWeekdayBetweenFixedParserTest extends FixedParserTest {

  private final FixedWeekdayBetweenFixedParser sut = new FixedWeekdayBetweenFixedParser();
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
    final FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setValidTo(2009);
    config.getFixedWeekdayBetweenFixed().add(e);

    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testWednesday() {
    final Holidays config = new Holidays();
    final FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setFrom(createFixed(17, Month.JANUARY));
    e.setTo(createFixed(21, Month.JANUARY));
    e.setWeekday(Weekday.WEDNESDAY);
    config.getFixedWeekdayBetweenFixed().add(e);

    final List<Holiday> holidays = sut.parse(2011, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Wrong number of results.");
    assertEquals(calendarUtil.create(2011, 1, 19), holidays.iterator().next().getDate(), "Wrong date.");
  }
}
