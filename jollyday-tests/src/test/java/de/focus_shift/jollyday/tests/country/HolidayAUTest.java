package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolidayAUTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "au";

  @ParameterizedTest
  @ValueSource(ints = {2019, 2020, 2021, 2022})
  void testManagerAUStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerAULoadFromUrl() {
    final HolidayManager calendarPartLoaded = HolidayManager.getInstance(ManagerParameters.create("test_au_2020"));
    final HolidayManager urlLoaded = HolidayManager.getInstance(
      ManagerParameters.create(AbstractCountryTestBase.class.getClassLoader().getResource("holidays/Holidays_test_au_2020.xml"))
    );

    final CalendarHierarchy dataHierarchy = calendarPartLoaded.getCalendarHierarchy();
    final CalendarHierarchy testHierarchy = urlLoaded.getCalendarHierarchy();

    compareHierarchies(testHierarchy, dataHierarchy);
    compareData(urlLoaded, calendarPartLoaded, 2020, true);
  }
}
