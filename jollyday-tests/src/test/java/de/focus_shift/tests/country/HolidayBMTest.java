package de.focus_shift.tests.country;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.country.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayBMTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "bm";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @ParameterizedTest
  @ValueSource(ints = {2017, 2018, 2020, 2021, 2022, 2023, 2024, 2025})
  void testManagerVGStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerVGInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BERMUDA, null));
      final LocalDate startDateInclusive = calendarUtil.create(2022, 10, 1);
      final LocalDate endDateInclusive = calendarUtil.create(2023, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(calendarUtil.create(2022, 11, 11),
        calendarUtil.create(2022, 12, 26), calendarUtil.create(2022, 12, 27),
        calendarUtil.create(2023, 1, 2));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(calendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerDifferentInstance() {
    final Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.US);
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager virginIslandsManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BERMUDA, null));
      assertThat(defaultManager).isNotEqualTo(virginIslandsManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }
}
