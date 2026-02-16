package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractCountryTestBase {

  protected void validateCalendarData(final String countryCode, Year year) {
    validateCalendarData(countryCode, year, false);
  }

  protected void validateCalendarData(final String countryCode, Year year, boolean assertAllHolidaysChecked) {
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

    assertThat(found.getChildren())
      .withFailMessage("Number of %s children should be %s but are %s", found.getDescription(), expected.getChildren().size(), found.getChildren().size())
      .hasSameSizeAs(expected.getChildren());

    for (String id : expected.getChildren().keySet()) {
      assertThat(found.getChildren()).containsKey(id);
      compareHierarchies(expected.getChildren().get(id), found.getChildren().get(id));
    }
  }

  protected void compareData(HolidayManager expected, HolidayManager found, Year year, boolean assertAllHolidaysChecked) {
    compareDates(expected, found, expected.getCalendarHierarchy(), List.of(), year, assertAllHolidaysChecked);
  }

  private void compareDates(final HolidayManager expected, final HolidayManager found, final CalendarHierarchy expectedHierarchy, final List<String> args, Year year, boolean assertAllHolidaysChecked) {

    final Set<Holiday> expectedHolidays = expected.getHolidays(year, args.toArray(String[]::new));
    final Set<Holiday> foundHolidays = found.getHolidays(year, args.toArray(String[]::new));

    for (final Holiday expectedHoliday : expectedHolidays) {
      assertThat(expectedHoliday.getDescription()).isNotNull();
      if (!CalendarUtil.contains(foundHolidays, expectedHoliday.getDate())) {
        fail("Could not find " + expectedHoliday + " in " + expectedHierarchy.getDescription() + " -  " + foundHolidays);
      }
    }

    if (assertAllHolidaysChecked) {
      assertThat(expectedHolidays.stream().sorted().toList()).describedAs(expectedHierarchy.getDescription())
        .containsAll(foundHolidays.stream().sorted().toList());
    }

    for (String id : expectedHierarchy.getChildren().keySet()) {
      final List<String> newArgs = new ArrayList<>(args);
      newArgs.add(id);
      compareDates(expected, found, expectedHierarchy.getChildren().get(id), newArgs, year, assertAllHolidaysChecked);
    }
  }
}
