package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayESTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "es";
  private static final int YEAR = 2010;

  @Test
  void testManagerESStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
