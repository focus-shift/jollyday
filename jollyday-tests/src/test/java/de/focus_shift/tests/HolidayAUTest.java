package de.focus_shift.tests;

import de.focus_shift.CalendarHierarchy;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayAUTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "au";

  @Test
  void testManagerAUStructure2019BeforeUpdate()  {
    validateCalendarData(ISO_CODE, 2019);
  }

  @Test
  void testManagerAUStructure2020()  {
    validateCalendarData(ISO_CODE, 2020, true);
  }

  @Test
  void testManagerAUStructure2021()  {
    validateCalendarData(ISO_CODE, 2021, true);
  }

  @Test
  void testManagerAUStructure2022()  {
    validateCalendarData(ISO_CODE, 2022, true);
  }

  @Test
  void testManagerAULoadFromUrl() {
    HolidayManager calendarPartLoaded = HolidayManager.getInstance(ManagerParameters.create("test_au_2020"));
    HolidayManager urlLoaded = HolidayManager.getInstance(
      ManagerParameters.create(AbstractCountryTestBase.class.getClassLoader().getResource("holidays/Holidays_test_au_2020.xml"))
    );

    CalendarHierarchy dataHierarchy = calendarPartLoaded.getCalendarHierarchy();
    CalendarHierarchy testHierarchy = urlLoaded.getCalendarHierarchy();

    compareHierarchies(testHierarchy, dataHierarchy);
    compareData(urlLoaded, calendarPartLoaded, 2020, true);
  }
}
