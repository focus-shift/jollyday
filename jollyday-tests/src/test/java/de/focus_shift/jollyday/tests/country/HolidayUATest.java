package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;

class HolidayUATest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ua";

  @ParameterizedTest
  @ValueSource(strings = {"2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"})
  void testManagerUAStructure(final Year year) {
    validateCalendarData(ISO_CODE, year);
  }

}
