package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayISTest extends AbstractCountryTestBase {

  private static final Year YEAR = Year.of(2010);
  private static final String ISO_CODE = "is";

  @Test
  void testManagerISStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

}
