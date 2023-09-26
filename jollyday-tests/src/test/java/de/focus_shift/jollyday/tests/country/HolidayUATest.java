package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HolidayUATest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ua";

  @ParameterizedTest
  @ValueSource(ints = {2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020})
  void testManagerUAStructure(final int year) {
    validateCalendarData(ISO_CODE, year);
  }

}
