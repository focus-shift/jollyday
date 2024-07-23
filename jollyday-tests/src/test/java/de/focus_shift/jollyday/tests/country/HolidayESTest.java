package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayESTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "es";
  private static final Year YEAR = Year.of(2010);

  @Test
  void testManagerESStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
