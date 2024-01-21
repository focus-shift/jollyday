package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolidayUSTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "us";

  @ParameterizedTest
  @ValueSource(ints = {2010, 2022})
  void testManagerUSStructure(final int year) {
    validateCalendarData(ISO_CODE, year);
  }
}
