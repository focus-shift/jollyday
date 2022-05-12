package de.focus_shift.tests;

import java.time.LocalDate;
import java.util.Set;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HolidayAETest extends AbstractCountryTestBase {

  private static final int YEAR = 2019;

  private CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  public void testNumberOfHolidays() throws Exception {
    HolidayManager holidayManager = HolidayManager
      .getInstance(ManagerParameters.create(HolidayCalendar.UNITED_ARAB_EMIRATES));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(13, holidays.size());
  }

  @Test
  public void testRamadanEnd() throws Exception {
    LocalDate expected = calendarUtil.create(YEAR, 6, 3);
    HolidayManager holidayManager = HolidayManager
      .getInstance(ManagerParameters.create(HolidayCalendar.UNITED_ARAB_EMIRATES));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(13, holidays.size());
    assertTrue(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("islamic.RAMADAN_END")
        && holiday.getDate().equals(expected)).count() == 1,
      "Wrong / missing holiday for Ramadan End");
  }

}
