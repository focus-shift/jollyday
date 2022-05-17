package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.parser.impl.FixedWeekdayInMonthParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author svdi1de
 */
class FixedWeekdayInMonthParserTest {

  private final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final FixedWeekdayInMonth e = new FixedWeekdayInMonth();
    e.setValidFrom(2011);
    config.getFixedWeekday().add(e);

    final List<Holiday> holidays = sut.parse(2010, new JaxbHolidays(config));
    assertEquals(0, holidays.size(), "Expected to be empty.");
  }
}
