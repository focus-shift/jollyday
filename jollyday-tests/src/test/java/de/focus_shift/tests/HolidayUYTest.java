package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Uruguay holidays agreed with Law N. 17.414
 *
 * @author jmoreno
 * @see <a href="http://www.impo.com.uy/bases/leyes/17414-2001">Ley Nº
 * 17.414<a/>
 */
class HolidayUYTest extends AbstractCountryTestBase {

  private HolidayManager holidayManager;

  @BeforeEach
  void init() {
    holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.URUGUAY));
  }

  @Test
  void testManagerUYStructure() {
    validateCalendarData("uy", 2016);
  }

  @Test
  void testManagerUYLanding33EasternersMovingDaysWhenLanding33EasternersOnTuesday() {
    int year = 2016;
    LocalDate landing33Easterners = LocalDate.of(year, 4, 18);
    assertTrue(contains(landing33Easterners, holidayManager.getHolidays(year)),
      "There should be LANDING_33_EASTERNERS on " + landing33Easterners);
  }

  @Test
  void testManagerUYLanding33EasternersMovingDaysWhenLanding33EasternersOnThursday() {
    int year = 2007;
    LocalDate landing33Easterners = LocalDate.of(year, 4, 23);
    assertTrue(contains(landing33Easterners, holidayManager.getHolidays(year)),
      "There should be LANDING_33_EASTERNERS on " + landing33Easterners);
  }

  @Test
  void testManagerUYRaceMovingDaysWhenRaceOnWednesday() {
    int year = 2016;
    LocalDate race = LocalDate.of(year, 10, 10);
    assertTrue(contains(race, holidayManager.getHolidays(year)),
      "There should be RACE on " + race);
  }

  @Test
  void testManagerUYRaceMovingDaysWhenRaceOnFriday() {
    int year = 2007;
    LocalDate race = LocalDate.of(year, 10, 15);
    assertTrue(contains(race, holidayManager.getHolidays(year)),
      "There should be RACE on " + race);
  }

  @Test
  void testManagerUYLasPiedrasMovingDaysWhenLasPiedrasOnTuesday() {
    int year = 2016;
    LocalDate lasPiedras = LocalDate.of(year, 5, 16);
    assertTrue(contains(lasPiedras, holidayManager.getHolidays(year)),
      "There should be LAS_PIEDRAS on " + lasPiedras);
  }

  @Test
  void testManagerUYLasPiedrasMovingDaysWhenLasPiedrasOnFriday() {
    int year = 2007;
    LocalDate lasPiedras = LocalDate.of(year, 5, 21);
    assertTrue(contains(lasPiedras, holidayManager.getHolidays(year)),
      "There should be LAS_PIEDRAS on " + lasPiedras);
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
