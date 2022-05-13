package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayFITest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fi";
  private static final int YEAR = 2010;

  @Test
  void testManagerFIStructure()  {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
