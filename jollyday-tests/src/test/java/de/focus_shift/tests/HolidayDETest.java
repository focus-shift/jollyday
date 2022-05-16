package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayDETest extends AbstractCountryTestBase {

  private static final int YEAR = 2010;
  private static final String ISO_CODE = "de";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testManagerDEStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testManagerDEInterval() {
    try {
      HolidayManager instance = HolidayManager.getInstance(HolidayCalendar.GERMANY);
      Set<Holiday> holidays = instance.getHolidays(calendarUtil.create(2010, 10, 1), calendarUtil
        .create(2011, 1, 31));
      List<LocalDate> expected = Arrays.asList(calendarUtil.create(2010, 12, 25),
        calendarUtil.create(2010, 12, 26), calendarUtil.create(2010, 10, 3),
        calendarUtil.create(2011, 1, 1));
      assertEquals(expected.size(), holidays.size(), "Wrong number of holidays");
      for (LocalDate d : expected) {
        assertTrue(calendarUtil.contains(holidays, d), "Expected date " + d + " missing.");
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerSameInstance() {
    Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.GERMANY);
    try {
      HolidayManager defaultManager = HolidayManager.getInstance();
      HolidayManager germanManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
      assertEquals(defaultManager, germanManager, "Unexpected manager found");
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  @Test
  void testManagerDifferentInstance() {
    Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.US);
    try {
      HolidayManager defaultManager = HolidayManager.getInstance();
      HolidayManager germanManager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
      assertNotSame(defaultManager, germanManager, "Unexpected manager found");
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  @Test
  void testSystemLocaleInfluence() {
    Set<Holiday> french = getUsingSystemLocale(Locale.FRANCE);
    Set<Holiday> german = getUsingSystemLocale(Locale.GERMANY);
    assertEquals(german, french, "Holidays differ.");
  }

  private Set<Holiday> getUsingSystemLocale(Locale systemLocale) {
    Locale defaultLocale = Locale.getDefault();
    try {
      Locale.setDefault(systemLocale);
      ManagerParameter parameters = ManagerParameters.create(Locale.GERMAN);
      HolidayManager mgr = HolidayManager.getInstance(parameters);
      return mgr.getHolidays(2018);
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }
}
