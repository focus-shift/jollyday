package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES;
import static java.util.Locale.FRANCE;
import static java.util.Locale.US;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractCountryTestBase {


  protected void validateCalendarData(final String countryCode, int year) {
    validateCalendarData(countryCode, year, false);
  }

  protected void validateCalendarData(final String countryCode, int year, boolean assertAllHolidaysChecked) {
    final HolidayManager dataManager = HolidayManager.getInstance(ManagerParameters.create(countryCode));
    final HolidayManager testManager = HolidayManager.getInstance(ManagerParameters.create("test_" + countryCode + "_" + year));

    final CalendarHierarchy dataHierarchy = dataManager.getCalendarHierarchy();
    final CalendarHierarchy testHierarchy = testManager.getCalendarHierarchy();

    compareHierarchies(testHierarchy, dataHierarchy);
    compareData(testManager, dataManager, year, assertAllHolidaysChecked);
  }

  /**
   * Compares two hierarchy structure by traversing down.
   *
   * @param expected This is the test structure which is how it should be.
   * @param found    This is the real live data structure.
   */
  protected void compareHierarchies(CalendarHierarchy expected, CalendarHierarchy found) {
    assertThat(found.getDescription()).isNotNull();
    assertThat(found.getId()).isEqualTo(expected.getId());
    assertThat(found.getChildren()).hasSize(expected.getChildren().size());
    for (String id : expected.getChildren().keySet()) {
      assertThat(found.getChildren()).containsKey(id);
      compareHierarchies(expected.getChildren().get(id), found.getChildren().get(id));
    }
  }

  protected void compareData(HolidayManager expected, HolidayManager found, int year, boolean assertAllHolidaysChecked) {
    compareDates(expected, found, expected.getCalendarHierarchy(), List.of(), year, assertAllHolidaysChecked);
  }

  /**
   * Validate Country calendar and Default calendar is same if local default
   * is set to country local
   *
   * @param countryLocale
   * @param countryCalendar
   */
  protected void validateManagerSameInstance(Locale countryLocale, HolidayCalendar countryCalendar) {
    final Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(countryLocale);
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager countryManager = HolidayManager.getInstance(ManagerParameters.create(countryCalendar, null));
      assertThat(countryManager).isEqualTo(defaultManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  protected void validateManagerDifferentInstance(final HolidayCalendar countryCalendar) {
    final Locale defaultLocale = Locale.getDefault();
    if (countryCalendar == UNITED_STATES) {
      Locale.setDefault(FRANCE);
    } else {
      Locale.setDefault(US);
    }
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager countryManager = HolidayManager.getInstance(ManagerParameters.create(countryCalendar, null));
      assertThat(countryManager).isNotEqualTo(defaultManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  private void compareDates(final HolidayManager expected, final HolidayManager found, final CalendarHierarchy expectedHierarchy, final List<String> args, int year, boolean assertAllHolidaysChecked) {

    final Set<Holiday> expectedHolidays = expected.getHolidays(year, args.toArray(String[]::new));
    final Set<Holiday> foundHolidays = found.getHolidays(year, args.toArray(String[]::new));

    for (final Holiday expectedHoliday : expectedHolidays) {
      assertThat(expectedHoliday.getDescription()).isNotNull();
      if (!CalendarUtil.contains(foundHolidays, expectedHoliday.getDate())) {
        fail("Could not find " + expectedHoliday + " in " + expectedHierarchy.getDescription() + " - " + foundHolidays);
      }
    }

    if (assertAllHolidaysChecked) {
      assertThat(expectedHolidays.stream().sorted().collect(toList())).describedAs(expectedHierarchy.getDescription())
        .containsAll(foundHolidays.stream().sorted().collect(toList()));
    }

    for (String id : expectedHierarchy.getChildren().keySet()) {
      final List<String> newArgs = new ArrayList<>(args);
      newArgs.add(id);
      compareDates(expected, found, expectedHierarchy.getChildren().get(id), newArgs, year, assertAllHolidaysChecked);
    }
  }
}
