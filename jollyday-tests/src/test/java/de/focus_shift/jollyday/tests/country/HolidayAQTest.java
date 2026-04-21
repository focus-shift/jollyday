package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANTARCTICA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayAQTest {

  @Test
  void ensuresHolidays() {
    // Antarctica has no official holidays as it has no population or government of its own
    assertFor(ANTARCTICA).check();
  }
}
