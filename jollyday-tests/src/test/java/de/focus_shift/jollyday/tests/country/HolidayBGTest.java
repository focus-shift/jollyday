package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayBGTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "bg";
  private static final Year YEAR = Year.of(2010);

  @Test
  void testManagerBGStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }
}
