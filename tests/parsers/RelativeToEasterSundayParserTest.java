package de.focus_shift.tests.parsers;

import de.focus_shift.Holiday;
import de.focus_shift.jaxb.mapping.Holidays;
import de.focus_shift.jaxb.mapping.RelativeToEasterSunday;
import de.focus_shift.parser.impl.RelativeToEasterSundayParser;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelativeToEasterSundayParserTest {

  RelativeToEasterSundayParser parser = new RelativeToEasterSundayParser();
  Set<Holiday> holidays = new HashSet<>();
  CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testForEasterMonday() {
    doTest(2013, 1);
  }

  @Test
  void testForEasterSaturday() {
    doTest(2013, -1);
  }

  private void doTest(int year, int days) {
    Holidays holidaysConfig = new Holidays();
    addRelativeToEasterHoliday(holidaysConfig, days);
    parser.parse(year, holidays, holidaysConfig);
    assertEquals(1, holidays.size(), "Missing holiday.");
    Holiday h = holidays.iterator().next();
    LocalDate targetDate = calendarUtil.getEasterSunday(year).plusDays(days);
    assertEquals(targetDate, h.getDate(), "Wrong date found.");
  }

  private void addRelativeToEasterHoliday(Holidays holidaysConfig, int days) {
    RelativeToEasterSunday r = new RelativeToEasterSunday();
    r.setDays(days);
    holidaysConfig.getRelativeToEasterSunday().add(r);
  }

}
