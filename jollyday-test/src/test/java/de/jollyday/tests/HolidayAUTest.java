package de.focus_shift.tests;

import de.focus_shift.CalendarHierarchy;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

public class HolidayAUTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "au";

  @Test
  public void testManagerAUStructure2019BeforeUpdate() throws Exception {
    validateCalendarData(ISO_CODE, 2019);
  }

  @Test
  public void testManagerAUStructure2020() throws Exception {
    validateCalendarData(ISO_CODE, 2020, true);
  }

  @Test
  public void testManagerAUStructure2021() throws Exception {
    validateCalendarData(ISO_CODE, 2021, true);
  }

  @Test
  public void testManagerAUStructure2022() throws Exception {
    validateCalendarData(ISO_CODE, 2022, true);
  }

  @Test
  public void testManagerAULoadFromUrl() {
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
