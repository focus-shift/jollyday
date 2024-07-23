package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

class HolidayMTTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "mt";

  @Test
  void ensureCorrectHolidaysForMalta() {
    validateCalendarData(ISO_CODE, Year.of(2023), true);
  }
}
