package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayUKTest extends AbstractCountryTestBase {

  private static final int YEAR = 2010;
  private static final String ISO_CODE = "gb";

  @Test
  void testManagerUKStructure()  {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testManagerUKChristmasMovingDaysWhenChristimasOnSunday() {
    doChristmasContainmentTest(2011, 26, 27);
  }

  @Test
  void testManagerUKChristmasMovingDaysWhenChristimasOnSaturday() {
    doChristmasContainmentTest(2010, 27, 28);
  }

  @Test
  void testManagerUKChristmasMovingDaysWhenChristimasOnFriday() {
    doChristmasContainmentTest(2009, 25, 28);
  }

  private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
    LocalDate christmas = LocalDate.of(year, 12, dayOfChristmas);
    LocalDate boxingday = LocalDate.of(year, 12, dayOfBoxingday);
    HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.UNITED_KINGDOM));
    Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertTrue(contains(christmas, holidays), "There should be christmas on " + christmas);
    assertTrue(contains(boxingday, holidays), "There should be boxing day on " + boxingday);
  }

  private boolean contains(LocalDate localDate, Set<Holiday> holidays) {
    for (Holiday h : holidays) {
      if (localDate.equals(h.getDate())) {
        return true;
      }
    }
    return false;
  }

}
