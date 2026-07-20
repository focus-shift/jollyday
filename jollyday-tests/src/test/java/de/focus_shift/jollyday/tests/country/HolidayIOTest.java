package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BRITISH_INDIAN_OCEAN_TERRITORY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayIOTest {

  @Test
  void ensuresHolidays() {
    // The British Indian Ocean Territory has no permanent civilian population or
    // legislature, and therefore no official public holidays.
    assertFor(BRITISH_INDIAN_OCEAN_TERRITORY).check();
  }
}
