package de.jollyday.tests.parsers;

import de.jollyday.Holiday;
import de.jollyday.config.EthiopianOrthodoxHoliday;
import de.jollyday.config.EthiopianOrthodoxHolidayType;
import de.jollyday.config.Holidays;
import de.jollyday.parser.impl.EthiopianOrthodoxHolidayParser;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sven
 */
public class EthiopianOrthodoxHolidayParserTest {

  private EthiopianOrthodoxHolidayParser parser = new EthiopianOrthodoxHolidayParser();
  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  public void testEmpty() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    parser.parse(2010, holidays, config);
    assertTrue(holidays.isEmpty(), "Expected to be empty.");
  }

  @Test
  public void testAllHolidays() {
    Set<Holiday> holidays = new HashSet<>();
    Holidays config = new Holidays();
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.ENKUTATASH));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.MESKEL));
    config.getEthiopianOrthodoxHoliday().add(createHoliday(EthiopianOrthodoxHolidayType.TIMKAT));
    parser.parse(2010, holidays, config);
    assertEquals(3, holidays.size(), "Wrong number of holidays.");
    assertContains(calendarUtil.create(2010, 1, 18), holidays);
    assertContains(calendarUtil.create(2010, 9, 11), holidays);
    assertContains(calendarUtil.create(2010, 9, 27), holidays);
  }

  private void assertContains(LocalDate date, Set<Holiday> holidays) {
    assertTrue(calendarUtil.contains(holidays, date), "Missing holiday " + date);
  }

  /**
   * @return an EthiopianOrthodoxHoliday instance
   */
  private EthiopianOrthodoxHoliday createHoliday(EthiopianOrthodoxHolidayType type) {
    EthiopianOrthodoxHoliday h = new EthiopianOrthodoxHoliday();
    h.setType(type);
    return h;
  }

}
