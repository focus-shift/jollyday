package de.jollyday.tests.parsers;

import de.jollyday.Holiday;
import de.jollyday.config.FixedWeekdayBetweenFixed;
import de.jollyday.config.Holidays;
import de.jollyday.config.Month;
import de.jollyday.config.Weekday;
import de.jollyday.parser.impl.FixedWeekdayBetweenFixedParser;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
public class FixedWeekdayBetweenFixedParserTest extends FixedParserTest {

  private FixedWeekdayBetweenFixedParser parser = new FixedWeekdayBetweenFixedParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  public void testEmpty() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  public void testInvalid() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setValidTo(2009);
    config.getFixedWeekdayBetweenFixed().add(e);
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  public void testWednesday() {
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
