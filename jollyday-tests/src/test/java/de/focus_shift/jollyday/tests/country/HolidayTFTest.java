package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayTFTest {

  @Test
  void ensuresNoHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS));

    assertThat(holidayManager.getHolidays(Year.of(2026))).isEmpty();
  }
}
