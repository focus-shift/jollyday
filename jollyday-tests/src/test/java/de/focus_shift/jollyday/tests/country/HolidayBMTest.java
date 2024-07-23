package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayBMTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "bm";


  @ParameterizedTest
  @ValueSource(strings = {"2015", "2016", "2017", "2018", "2020", "2021", "2022", "2023", "2024", "2025"})
  void testManagerBMStructure(final Year year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerBMInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BERMUDA, null));
      final LocalDate startDateInclusive = LocalDate.of(2022, 10, 1);
      final LocalDate endDateInclusive = LocalDate.of(2023, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2022, 11, 11),
        LocalDate.of(2022, 12, 26), LocalDate.of(2022, 12, 27),
        LocalDate.of(2023, 1, 2));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerDifferentInstanceBM() {
    validateManagerDifferentInstance(HolidayCalendar.BERMUDA);
  }
}
