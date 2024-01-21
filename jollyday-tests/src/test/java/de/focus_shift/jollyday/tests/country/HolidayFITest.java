package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

class HolidayFITest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fi";
  private static final int YEAR = 2010;

  @Test
  void testManagerFIStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
