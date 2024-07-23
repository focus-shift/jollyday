package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayALTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "al";
  private static final Year YEAR = Year.of(2010);

  @Test
  void testManagerALStructure() {
    validateCalendarData(ISO_CODE, YEAR, true);
  }
}
