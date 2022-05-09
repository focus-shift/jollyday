package de.jollyday.tests;

import java.time.LocalDate;
import java.util.Set;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;
import de.jollyday.tests.base.AbstractCountryTestBase;
import de.jollyday.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HolidayNZTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "nz";
  private static final int YEAR = 2018;

  private final CalendarUtil calendarUtil = new CalendarUtil();
  private final HolidayManager holidayManager = HolidayManager
    .getInstance(ManagerParameters.create(HolidayCalendar.NEW_ZEALAND));

  @Test
  public void testManagerNZStructure() throws Exception {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  public void testSouthlandAnniversary2011() {
    // Monday closest to 17 January
    LocalDate expected = calendarUtil.create(2011, 1, 17);
    Set<Holiday> holidays = holidayManager.getHolidays(2011, "stl");

    boolean found = holidays.stream().anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY")
      && holiday.getDate().equals(expected));
    assertTrue(found, "Did not find expected Southland Anniversary day at " + expected + " in 2011: " + holidays);
  }

  @Test
  public void testSouthlandAnniversary2012() {
    // Easter Tuesday
    LocalDate expected = calendarUtil.create(2012, 4, 10);
    Set<Holiday> holidays = holidayManager.getHolidays(2012, "stl");

    boolean found = holidays.stream().anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY")
      && holiday.getDate().equals(expected));
    assertTrue(found, "Did not find expected Southland Anniversary day at " + expected + " in 2012: " + holidays);
  }

}
