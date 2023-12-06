package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.NEW_ZEALAND;
import static de.focus_shift.jollyday.core.ManagerParameters.*;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNZTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "nz";
  private static final int YEAR = 2018;

  private final CalendarUtil calendarUtil = new CalendarUtil();
  private final HolidayManager holidayManager = HolidayManager.getInstance(create(NEW_ZEALAND));

  @Test
  void testManagerNZStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testSouthlandAnniversary2011() {
    // Monday closest to 17 January
    final LocalDate expected = calendarUtil.create(2011, 1, 17);
    final Set<Holiday> holidays = holidayManager.getHolidays(2011, "stl");

    boolean found = holidays.stream()
      .anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY") && holiday.getDate().equals(expected));
    assertThat(found).isTrue();
  }

  @Test
  void testSouthlandAnniversary2012() {
    // Easter Tuesday
    final LocalDate expected = calendarUtil.create(2012, 4, 10);
    final Set<Holiday> holidays = holidayManager.getHolidays(2012, "stl");

    boolean found = holidays.stream()
      .anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY") && holiday.getDate().equals(expected));
    assertThat(found).isTrue();
  }
}
