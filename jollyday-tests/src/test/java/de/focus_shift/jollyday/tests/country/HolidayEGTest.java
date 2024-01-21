package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.EGYPT;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayEGTest extends AbstractCountryTestBase {

  private static final int YEAR = 2019;

  @Test
  void testNumberOfHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(EGYPT));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(17);
  }

  @Test
  void testEasterMonday2019() {
    final LocalDate expected = CalendarUtil.create(YEAR, 4, 29);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(EGYPT));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(17);
    assertThat(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("christian.EASTER_MONDAY")
      && holiday.getDate().equals(expected)).count()).isEqualTo(1);
  }

  @Test
  void testEidFitr2019() {
    final LocalDate expected = CalendarUtil.create(YEAR, 6, 4);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(EGYPT));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(17);
    assertThat(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("islamic.ID_AL_FITR")
      && holiday.getDate().equals(expected)).count()).isEqualTo(1);
  }

  @Test
  void testArafaat2019() {
    final LocalDate expected = CalendarUtil.create(YEAR, 8, 10);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(EGYPT));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(17);
    assertThat(holidays.stream().filter(holiday -> holiday.getPropertiesKey().equals("islamic.ARAFAAT")
      && holiday.getDate().equals(expected)).count()).isEqualTo(1);
  }
}
