package de.focus_shift.impl;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.JaxbHolidays;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.RelativeToEasterSunday;
import de.focus_shift.parser.impl.RelativeToEasterSundayParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativeToEasterSundayParserTest {

  private final RelativeToEasterSundayParser sut = new RelativeToEasterSundayParser();
  private final CalendarUtil calendarUtil = new CalendarUtil();

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

    final List<Holiday> holidays = sut.parse(year, new JaxbHolidays(config));
    assertEquals(1, holidays.size(), "Missing holiday.");

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
