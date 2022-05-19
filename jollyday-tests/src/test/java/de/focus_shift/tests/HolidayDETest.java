package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.util.Locale.FRANCE;
import static java.util.Locale.GERMAN;
import static java.util.Locale.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayDETest extends AbstractCountryTestBase {

  private static final int YEAR = 2010;
  private static final String ISO_CODE = "de";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testManagerDEStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testManagerDEInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.GERMANY, null));
      final LocalDate startDateInclusive = calendarUtil.create(2010, 10, 1);
      final LocalDate endDateInclusive = calendarUtil.create(2011, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(calendarUtil.create(2010, 12, 25),
        calendarUtil.create(2010, 12, 26), calendarUtil.create(2010, 10, 3),
        calendarUtil.create(2011, 1, 1));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(calendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    }
  }

  @Test
  void testManagerSameInstance() {
    final Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(GERMANY);
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager germanManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.GERMANY, null));
      assertThat(defaultManager).isEqualTo(germanManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  @Test
  void testManagerDifferentInstance() {
    final Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.US);
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager germanManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.GERMANY, null));
      assertThat(defaultManager).isNotEqualTo(germanManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }

  @Test
  void testSystemLocaleInfluence() {
    final Set<Holiday> french = getUsingSystemLocale(FRANCE);
    final Set<Holiday> german = getUsingSystemLocale(GERMANY);
    assertThat(german).isEqualTo(french);
  }

  private Set<Holiday> getUsingSystemLocale(Locale systemLocale) {
    final Locale defaultLocale = Locale.getDefault();
    try {
      Locale.setDefault(systemLocale);
      final ManagerParameter parameters = ManagerParameters.create(GERMAN);
      final HolidayManager mgr = HolidayManager.getInstance(parameters);
      return mgr.getHolidays(2018);
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }
}
