package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidaySETest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "se";
  private static final Year YEAR = Year.of(2016);

  @Test
  void testManagerSEStructure() {
    validateCalendarData(ISO_CODE, YEAR, true);
  }
}
