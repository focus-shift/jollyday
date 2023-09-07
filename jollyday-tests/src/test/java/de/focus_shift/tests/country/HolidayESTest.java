package de.focus_shift.tests.country;

import de.focus_shift.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayESTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "es";
  private static final int YEAR = 2010;

  @Test
  void testManagerESStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
