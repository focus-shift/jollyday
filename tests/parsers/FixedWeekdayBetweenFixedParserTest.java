package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.Month;
import de.focus_shift.jaxb.mapping.Weekday;
import de.focus_shift.parser.impl.FixedWeekdayBetweenFixedParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
class FixedWeekdayBetweenFixedParserTest extends FixedParserTest {

  private FixedWeekdayBetweenFixedParser parser = new FixedWeekdayBetweenFixedParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testEmpty() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testInvalid() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setValidTo(2009);
    config.getFixedWeekdayBetweenFixed().add(e);
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testWednesday() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setFrom(createFixed(17, Month.JANUARY));
    e.setTo(createFixed(21, Month.JANUARY));
    e.setWeekday(Weekday.WEDNESDAY);
    config.getFixedWeekdayBetweenFixed().add(e);
    parser.parse(2011, holidays, config);
    assertEquals(1, holidays.size(), "Wrong number of results.");
    assertEquals(calendarUtil.create(2011, 1, 19), holidays.iterator().next().getDate(), "Wrong date.");
  }

}
