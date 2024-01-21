package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_ARAB_EMIRATES;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAETest extends AbstractCountryTestBase {

  private static final int YEAR = 2019;


  @Test
  void testNumberOfHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(UNITED_ARAB_EMIRATES));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(13);
  }

  @Test
  void testRamadanEnd() {
    final LocalDate expected = CalendarUtil.create(YEAR, 6, 3);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(UNITED_ARAB_EMIRATES));
    final Set<Holiday> holidays = holidayManager.getHolidays(YEAR);
    assertThat(holidays).hasSize(13);
    final Stream<Holiday> holidayStream = holidays.stream()
      .filter(holiday -> holiday.getPropertiesKey().equals("islamic.RAMADAN_END") && holiday.getDate().equals(expected));
    assertThat(holidayStream).hasSize(1);
  }
}
