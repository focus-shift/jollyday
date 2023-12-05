package de.focus_shift.jollyday.tests.jackson.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.FixedWeekdayInMonthParser;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.Holidays;

/**
 * @author svdi1de
 */
class FixedWeekdayInMonthParserTest {

  private final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final FixedWeekdayInMonth e = new FixedWeekdayInMonth();
    e.setValidFrom(2011);
    config.getFixedWeekday().add(e);

    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }
}
