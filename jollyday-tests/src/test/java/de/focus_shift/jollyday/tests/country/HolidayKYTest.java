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

class HolidayKYTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ky";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @ParameterizedTest
  @ValueSource(ints = {2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023})
  void testManagerKYStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerKYInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.CAYMAN_ISLANDS, null));
      final LocalDate startDateInclusive = calendarUtil.create(2022, 10, 1);
      final LocalDate endDateInclusive = calendarUtil.create(2023, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(calendarUtil.create(2022, 11, 14),
        calendarUtil.create(2022, 12, 26), calendarUtil.create(2022, 12, 27),
        calendarUtil.create(2023, 1, 2), calendarUtil.create(2023, 1, 23));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(calendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerDifferentInstanceKY() {
    validateManagerDifferentInstance(HolidayCalendar.CAYMAN_ISLANDS);
  }
}
