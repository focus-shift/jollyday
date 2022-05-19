package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayNZTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "nz";
  private static final int YEAR = 2018;

  private final CalendarUtil calendarUtil = new CalendarUtil();
  private final HolidayManager holidayManager = HolidayManager
    .getInstance(ManagerParameters.create(HolidayCalendar.NEW_ZEALAND));

  @Test
  void testManagerNZStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testSouthlandAnniversary2011() {
    // Monday closest to 17 January
    LocalDate expected = calendarUtil.create(2011, 1, 17);
    Set<Holiday> holidays = holidayManager.getHolidays(2011, "stl");

    boolean found = holidays.stream().anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY")
      && holiday.getDate().equals(expected));
    assertTrue(found, "Did not find expected Southland Anniversary day at " + expected + " in 2011: " + holidays);
  }

  @Test
  void testSouthlandAnniversary2012() {
    // Easter Tuesday
    LocalDate expected = calendarUtil.create(2012, 4, 10);
    Set<Holiday> holidays = holidayManager.getHolidays(2012, "stl");

    boolean found = holidays.stream().anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY")
      && holiday.getDate().equals(expected));
    assertTrue(found, "Did not find expected Southland Anniversary day at " + expected + " in 2012: " + holidays);
  }

}
