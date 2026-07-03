package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_VINCENT_AND_THE_GRENADINES;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.temporal.TemporalAdjusters.firstInMonth;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayVCTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_VINCENT_AND_THE_GRENADINES)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", MARCH, 14).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", OCTOBER, 27).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }

  @ParameterizedTest
  @ValueSource(ints = {2022, 2023, 2024, 2025, 2026})
  void ensuresCarnivalOnFirstMondayAndTuesdayOfJuly(final int year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_VINCENT_AND_THE_GRENADINES));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    final LocalDate firstMondayOfJuly = LocalDate.of(year, JULY, 1).with(firstInMonth(MONDAY));
    final LocalDate firstTuesdayOfJuly = LocalDate.of(year, JULY, 1).with(firstInMonth(TUESDAY));

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CARNIVAL_MONDAY"))
      .extracting(Holiday::getDate)
      .containsExactly(firstMondayOfJuly);

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CARNIVAL_TUESDAY"))
      .extracting(Holiday::getDate)
      .containsExactly(firstTuesdayOfJuly);
  }
}
