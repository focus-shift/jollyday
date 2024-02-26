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

class HolidayHKTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "hk";


  @ParameterizedTest
  @ValueSource(ints = {2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024})
  void testManagerHKStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerHKInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.HONG_KONG, null));
      final LocalDate startDateInclusive = LocalDate.of(2022, 10, 1);
      final LocalDate endDateInclusive = LocalDate.of(2023, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2022, 10, 1),
        LocalDate.of(2022, 10, 4), LocalDate.of(2022, 12, 26),
        LocalDate.of(2022, 12, 27), LocalDate.of(2023, 1, 2),
        LocalDate.of(2023, 1, 23), LocalDate.of(2023, 1, 24),
        LocalDate.of(2023, 1, 25));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerDifferentInstanceHK() {
    validateManagerDifferentInstance(HolidayCalendar.HONG_KONG);
  }
}
