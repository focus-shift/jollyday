package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayALTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "al";
  private static final int YEAR = 2010;

  @Test
  void testManagerALStructure()  {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
