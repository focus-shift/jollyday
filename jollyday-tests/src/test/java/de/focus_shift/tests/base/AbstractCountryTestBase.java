package de.focus_shift.tests.base;

import de.focus_shift.CalendarHierarchy;
import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractCountryTestBase {

  private final CalendarUtil calendarUtil = new CalendarUtil();

  /**
   * Compares two hierarchy structure by traversing down.
   *
   * @param expected This is the test structure which is how it should be.
   * @param found    This is the real live data structure.
   */
  protected void compareHierarchies(CalendarHierarchy expected, CalendarHierarchy found) {
    assertNotNull(found.getDescription(), "Null description");
    assertEquals(expected.getId(), found.getId(), "Wrong hierarchy id.");
    assertEquals(expected.getChildren().size(), found.getChildren().size(), "Number of children of '" + expected.getDescription() + "' wrong.");
    for (String id : expected.getChildren().keySet()) {
      assertTrue(found.getChildren().containsKey(id), "Missing " + id + " within " + found.getId());
      compareHierarchies(expected.getChildren().get(id), found.getChildren().get(id));
    }
  }

  protected void compareData(HolidayManager expected, HolidayManager found, int year, boolean assertAllHolidaysChecked) {
    CalendarHierarchy expectedHierarchy = expected.getCalendarHierarchy();
    List<String> args = new ArrayList<>();
    compareDates(expected, found, expectedHierarchy, args, year, assertAllHolidaysChecked);
  }

  private void compareDates(HolidayManager expected, HolidayManager found, CalendarHierarchy h, final List<String> args, int year, boolean assertAllHolidaysChecked) {
    Set<Holiday> expectedHolidays = expected.getHolidays(year, args.toArray(new String[]{}));
    Set<Holiday> foundHolidays = found.getHolidays(year, args.toArray(new String[]{}));
    for (Holiday expectedHoliday : expectedHolidays) {
      assertNotNull(expectedHoliday.getDescription(), "Description is null.");
      if (!calendarUtil.contains(foundHolidays, expectedHoliday.getDate())) {
        fail("Could not find " + expectedHoliday + " in " + h.getDescription() + " - " + foundHolidays);
      }
    }

    if (assertAllHolidaysChecked) {
      foundHolidays.removeAll(expectedHolidays);
      assertThat(foundHolidays).isEmpty();
    }

    for (String id : h.getChildren().keySet()) {
      ArrayList<String> newArgs = new ArrayList<>(args);
      newArgs.add(id);
      compareDates(expected, found, h.getChildren().get(id), newArgs, year, assertAllHolidaysChecked);
    }
  }

  protected void validateCalendarData(final String countryCode, int year) {
    validateCalendarData(countryCode, year, false);
  }

  protected void validateCalendarData(final String countryCode, int year, boolean assertAllHolidaysChecked) {
    HolidayManager dataManager = HolidayManager.getInstance(countryCode);
    HolidayManager testManager = HolidayManager.getInstance("test_" + countryCode + "_" + year);

    CalendarHierarchy dataHierarchy = dataManager.getCalendarHierarchy();
    CalendarHierarchy testHierarchy = testManager.getCalendarHierarchy();

    compareHierarchies(testHierarchy, dataHierarchy);
    compareData(testManager, dataManager, year, assertAllHolidaysChecked);
  }

  /**
   * Validate Country calendar and Default calendar is same if local default
   * is set to country local
   *
   * @param countryLocale
   * @param countryCalendar
   */
  protected void validateManagerSameInstance(Locale countryLocale, HolidayCalendar countryCalendar) {
    Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(countryLocale);
    try {
      HolidayManager defaultManager = HolidayManager.getInstance();
      HolidayManager countryManager = HolidayManager.getInstance(countryCalendar);
      assertEquals(defaultManager, countryManager, "Unexpected manager found");
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  protected void validateManagerDifferentInstance(HolidayCalendar countryCalendar) {
    Locale defaultLocale = Locale.getDefault();
    if (countryCalendar == HolidayCalendar.UNITED_STATES) {
      Locale.setDefault(Locale.FRANCE);
    } else {
      Locale.setDefault(Locale.US);
    }
    try {
      HolidayManager defaultManager = HolidayManager.getInstance();
      HolidayManager countryManager = HolidayManager.getInstance(countryCalendar);
      assertNotSame(defaultManager, countryManager, "Unexpected manager found");
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

}
