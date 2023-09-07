package de.focus_shift.tests.country;

import de.focus_shift.HolidayCalendar;
import de.focus_shift.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

import java.util.Locale;

class HolidayFRTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fr";
  private static final int YEAR = 2010;

  @Test
  void testManagerFRStructure() {
    validateCalendarData(ISO_CODE, YEAR);
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
