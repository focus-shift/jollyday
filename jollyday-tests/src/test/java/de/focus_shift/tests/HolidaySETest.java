package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidaySETest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "se";
  private static final int YEAR = 2016;

  @Test
  void testManagerSEStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
