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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayEGTest extends AbstractCountryTestBase {

  private static final int YEAR = 2019;

  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testNumberOfHolidays()  {
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.EGYPT));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(17, holidays.size());
  }

  @Test
  void testEasterMonday2019()  {
    LocalDate expected = calendarUtil.create(YEAR, 4, 29);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.EGYPT));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(17, holidays.size());
    assertTrue(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("christian.EASTER_MONDAY")
        && holiday.getDate().equals(expected)).count() == 1,
      "Wrong / missing holiday for Easter Monday");
  }

  @Test
  void testEidFitr2019()  {
    LocalDate expected = calendarUtil.create(YEAR, 6, 4);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.EGYPT));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(17, holidays.size());
    assertTrue(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("islamic.ID_AL_FITR")
        && holiday.getDate().equals(expected)).count() == 1,
      "Wrong / missing holiday for Eid Fitr");
  }

  @Test
  void testArafaat2019()  {
    LocalDate expected = calendarUtil.create(YEAR, 8, 10);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.EGYPT));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(17, holidays.size());
    assertTrue(holidays.stream().filter(
          holiday -> holiday.getPropertiesKey().equals("islamic.ARAFAAT") && holiday.getDate().equals(expected))
        .count() == 1,
      "Wrong / missing holiday for Arafaat");
  }

}
