package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayCHTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ch";

  @Test
  void testManagerCHStructure() {
    validateCalendarData(ISO_CODE, 2022, true);
  }
}
