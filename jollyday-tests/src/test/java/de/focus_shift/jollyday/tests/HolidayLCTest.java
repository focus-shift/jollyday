package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_LUCIA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayLCTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_LUCIA)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("DAY_AFTER_NEW_YEAR", JANUARY, 2).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 22).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", DECEMBER, 13).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }

  @ParameterizedTest
  @ValueSource(ints = {2022, 2023, 2024, 2025, 2026})
  void ensuresThanksgivingOnFirstMondayOfOctober(final int year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_LUCIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    final LocalDate firstMondayOfOctober = LocalDate.of(year, OCTOBER, 1).with(firstInMonth(MONDAY));

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("THANKSGIVING"))
      .extracting(Holiday::getDate)
      .containsExactly(firstMondayOfOctober);
  }
}
