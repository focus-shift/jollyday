package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidayMTTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "mt";

  @Test
  void ensureCorrectHolidaysForMalta() {
    validateCalendarData(ISO_CODE, 2023, true);
  }
}
