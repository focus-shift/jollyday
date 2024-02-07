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
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayVGTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "vg";


  @ParameterizedTest
  @ValueSource(ints = {2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024})
  void testManagerVGStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerVGInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BRITISH_VIRGIN_ISLANDS, null));
      final LocalDate startDateInclusive = CalendarUtil.create(2015, 10, 1);
      final LocalDate endDateInclusive = CalendarUtil.create(2016, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(CalendarUtil.create(2015, 12, 25),
        CalendarUtil.create(2015, 12, 28), CalendarUtil.create(2015, 10, 19),
        CalendarUtil.create(2016, 1, 1));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerDifferentInstanceVG() {
    validateManagerDifferentInstance(HolidayCalendar.BRITISH_VIRGIN_ISLANDS);
  }
}
