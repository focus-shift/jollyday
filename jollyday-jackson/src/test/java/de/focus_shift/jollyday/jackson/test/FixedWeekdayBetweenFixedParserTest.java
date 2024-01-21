package de.focus_shift.jollyday.jackson.test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.FixedWeekdayBetweenFixedParser;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.Month;
import de.focus_shift.jollyday.jackson.mapping.Weekday;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author svdi1de
 */
class FixedWeekdayBetweenFixedParserTest extends FixedParserTest {

  private final FixedWeekdayBetweenFixedParser sut = new FixedWeekdayBetweenFixedParser();

  @Test
  void testEmpty() {
    final Holidays config = new Holidays();
    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testInvalid() {
    final Holidays config = new Holidays();
    final FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setValidTo(2009);
    config.getFixedWeekdayBetweenFixed().add(e);

    final List<Holiday> holidays = sut.parse(2010, new JacksonHolidays(config));
    assertThat(holidays).isEmpty();
  }

  @Test
  void testWednesday() {
    final Holidays config = new Holidays();
    final FixedWeekdayBetweenFixed e = new FixedWeekdayBetweenFixed();
    e.setFrom(createFixed(17, Month.JANUARY));
    e.setTo(createFixed(21, Month.JANUARY));
    e.setWeekday(Weekday.WEDNESDAY);
    config.getFixedWeekdayBetweenFixed().add(e);

    final List<Holiday> holidays = sut.parse(2011, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);
    assertThat(holidays.iterator().next().getDate()).isEqualTo(CalendarUtil.create(2011, 1, 19));
  }
}
