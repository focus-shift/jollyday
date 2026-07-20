package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.HEARD_ISLAND_AND_MCDONALD_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;

class HolidayHMTest {

  @Test
  void ensuresHolidays() {
    // Heard Island and McDonald Islands are uninhabited and have no local government,
    // and therefore no official public holidays.
    assertFor(HEARD_ISLAND_AND_MCDONALD_ISLANDS).check();
  }
}
