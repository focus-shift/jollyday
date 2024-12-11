package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static java.util.Locale.FRANCE;
import static java.util.Locale.GERMAN;
import static java.util.Locale.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayDETest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "de";

  @ParameterizedTest
  @ValueSource(strings = {"2010", "2022", "2023"})
  void testManagerDEStructure(final Year year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerDEInterval() {
    try {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.GERMANY, null));
      final LocalDate startDateInclusive = LocalDate.of(2010, 10, 1);
      final LocalDate endDateInclusive = LocalDate.of(2011, 1, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2010, 12, 25),
        LocalDate.of(2010, 12, 26), LocalDate.of(2010, 10, 3),
        LocalDate.of(2011, 1, 1));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
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
      return mgr.getHolidays(Year.of(2018));
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }
}
