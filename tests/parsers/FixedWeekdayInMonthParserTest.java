package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.parser.impl.FixedWeekdayInMonthParser;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
class FixedWeekdayInMonthParserTest {

  private FixedWeekdayInMonthParser parser = new FixedWeekdayInMonthParser();

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
    FixedWeekdayInMonth e = new FixedWeekdayInMonth();
    e.setValidFrom(2011);
    config.getFixedWeekday().add(e);
    parser.parse(2010, holidays, config);
    assertEquals(0, holidays.size(), "Expected to be empty.");
  }

}
