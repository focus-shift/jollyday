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

import static org.assertj.core.api.Assertions.assertThat;

class HolidayTRTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "tr";
  private static final int YEAR = 2019;

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testManagerTRStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testNumberOfHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(9);
  }

  @Test
  void testRamazan2019() {
    // Actually, in Turkey, Ramadan is one day after Eid Mubarak, for keep the Eid al Fitr for now
    final LocalDate expected = calendarUtil.create(YEAR, 6, 4);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(9);
    boolean found = false;
    for (Holiday holiday : holidays) {
      if (holiday.getPropertiesKey().equals("islamic.ID_AL_FITR")) {
        if (holiday.getDate().equals(expected)) {
          found = true;
        }
      }
    }
    assertThat(found).isTrue();
  }

  @Test
  void testKurban2019() {
    final LocalDate expected = calendarUtil.create(YEAR, 8, 11);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.TURKEY));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(9);
    boolean found = false;
    for (Holiday holiday : holidays) {
      if (holiday.getPropertiesKey().equals("islamic.ID_UL_ADHA")) {
        if (holiday.getDate().equals(expected)) {
          found = true;
        }
      }
    }
    assertThat(found).isTrue();
  }
}
