package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolidayNYSETest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "nyse";

  @ParameterizedTest
  @ValueSource(ints = {2023, 2024, 2025, 2026})
  void testManagerNYSEStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }
}
