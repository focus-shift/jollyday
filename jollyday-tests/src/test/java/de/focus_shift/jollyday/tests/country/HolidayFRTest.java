package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayCalendar;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Locale;

class HolidayFRTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fr";
  private static final Year YEAR = Year.of(2010);

  @Test
  void testManagerFRStructure() {
    validateCalendarData(ISO_CODE, YEAR, true);
  }

  @Test
  void testManagerSameInstanceFR() {
    validateManagerSameInstance(Locale.FRANCE, HolidayCalendar.FRANCE);
  }

  @Test
  void testManagerDifferentInstanceFR() {
    validateManagerDifferentInstance(HolidayCalendar.FRANCE);
  }

}
