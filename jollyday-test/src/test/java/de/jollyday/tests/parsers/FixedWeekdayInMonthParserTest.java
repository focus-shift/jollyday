package de.focus_shift.tests.parsers;

import java.util.HashSet;
import java.util.Set;

import de.focus_shift.Holiday;
import de.focus_shift.config.FixedWeekdayInMonth;
import de.focus_shift.config.Holidays;
import de.focus_shift.parser.impl.FixedWeekdayInMonthParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
public class FixedWeekdayInMonthParserTest {

  private FixedWeekdayInMonthParser parser = new FixedWeekdayInMonthParser();

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
    FixedWeekdayInMonth e = new FixedWeekdayInMonth();
    e.setValidFrom(2011);
    config.getFixedWeekday().add(e);
    parser.parse(2010, holidays, config);
    assertEquals(0, holidays.size(), "Expected to be empty.");
  }

}
