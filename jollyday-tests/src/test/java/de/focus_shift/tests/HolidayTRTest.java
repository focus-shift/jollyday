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

class HolidayTRTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "tr";
  private static final int YEAR = 2019;

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testManagerTRStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testNumberOfHolidays() {
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(9, holidays.size());
  }

  @Test
  void testRamazan2019() {
    // Actually, in Turkey, Ramadan is one day after Eid Mubarak, for keep the Eid al Fitr for now
    LocalDate expected = calendarUtil.create(YEAR, 6, 4);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(9, holidays.size());
    boolean found = false;
    for (Holiday holiday : holidays) {
      if (holiday.getPropertiesKey().equals("islamic.ID_AL_FITR")) {
        if (holiday.getDate().equals(expected)) {
          found = true;
        }
      }
    }
    assertTrue(found, "Wrong / missing holiday for Ramazan");
  }

  @Test
  void testKurban2019() {
    LocalDate expected = calendarUtil.create(YEAR, 8, 11);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertEquals(9, holidays.size());
    boolean found = false;
    for (Holiday holiday : holidays) {
      if (holiday.getPropertiesKey().equals("islamic.ID_UL_ADHA")) {
        if (holiday.getDate().equals(expected)) {
          found = true;
        }
      }
    }
    assertTrue(found, "Wrong / missing holiday for Kurban");
  }
}
