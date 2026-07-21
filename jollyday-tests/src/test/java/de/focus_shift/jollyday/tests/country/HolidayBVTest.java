package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BOUVET_ISLAND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayBVTest {

  @Test
  void ensuresHolidays() {
    // Bouvet Island is an uninhabited Norwegian dependency with no permanent population or
    // legislature, and therefore no official public holidays.
    assertFor(BOUVET_ISLAND).check();
  }
}
