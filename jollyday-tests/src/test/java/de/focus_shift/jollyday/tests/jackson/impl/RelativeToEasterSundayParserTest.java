package de.focus_shift.jollyday.tests.jackson.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.impl.RelativeToEasterSundayParser;
import de.focus_shift.jollyday.jackson.JacksonHolidays;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday;

class RelativeToEasterSundayParserTest {

  private final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
  // private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testForEasterMonday() {
    doTest(2013, 1);
  }

  @Test
  void testForEasterSaturday() {
    doTest(2013, -1);
  }

  private void doTest(int year, int days) {
    final Holidays config = new Holidays();
    addRelativeToEasterHoliday(config, days);

    final List<Holiday> holidays = sut.parse(year, new JacksonHolidays(config));
    assertThat(holidays).hasSize(1);

/* TODO
    final Holiday holiday = holidays.iterator().next();
    final LocalDate targetDate = calendarUtil.getEasterSunday(year).plusDays(days);
    assertEquals(targetDate, holiday.getDate(), "Wrong date found.");*/
  }

  private void addRelativeToEasterHoliday(Holidays holidaysconfig, int days) {
    final RelativeToEasterSunday r = new RelativeToEasterSunday();
    r.setDays(days);
    holidaysconfig.getRelativeToEasterSunday().add(r);
  }
}
