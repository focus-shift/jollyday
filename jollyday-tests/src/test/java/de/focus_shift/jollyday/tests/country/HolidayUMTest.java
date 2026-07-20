package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES_MINOR_OUTLYING_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayUMTest {

  @Test
  void ensuresHolidays() {
    // The United States Minor Outlying Islands have no permanent residents or local
    // government, and therefore no official public holidays.
    assertFor(UNITED_STATES_MINOR_OUTLYING_ISLANDS).check();
  }
}
