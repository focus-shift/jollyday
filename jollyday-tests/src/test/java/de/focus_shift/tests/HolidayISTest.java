package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayISTest extends AbstractCountryTestBase {

  private static final int YEAR = 2010;
  private static final String ISO_CODE = "is";

  @Test
  void testManagerISStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
