package de.focus_shift.tests;

import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayLUTest extends AbstractCountryTestBase {

  private static final int YEAR = 2023;
  private static final String ISO_CODE = "lu";

  @Test
  void e() {
    validateCalendarData(ISO_CODE, 2023);
  }

}
