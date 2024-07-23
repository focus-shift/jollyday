package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayAUTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "au";

  @Property
  void testManagerAUStructure(@ForAll @YearRange(min = 2019, max = 2022) Year year) {
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
    compareData(urlLoaded, calendarPartLoaded, Year.of(2020), true);
  }
}
