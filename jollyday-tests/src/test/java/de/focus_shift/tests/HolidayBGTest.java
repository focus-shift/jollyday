package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayBGTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "bg";
  private static final int YEAR = 2010;

  @Test
  void testManagerBGStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
