package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.GUAM;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayGUTest {

  @Test
  void ensuresHolidays() {
    assertFor(GUAM)
      // Fixed date holidays - mondayisation per 1 GCA § 1000: Saturday -> previous Friday, Sunday -> next Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 4)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LIBERATION_DAY", JULY, 21)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("VETERANS_DAY", NOVEMBER, 11)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LADY_OF_CAMARIN_DAY", DECEMBER, 8)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @ParameterizedTest
  @ValueSource(ints = {2022, 2023, 2024, 2025, 2026})
  void ensuresFixedWeekdayHolidays(final int year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(GUAM));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    assertHolidayOn(holidays, "MARTIN_LUTHER_KING", LocalDate.of(year, JANUARY, 1).with(dayOfWeekInMonth(3, MONDAY)));
    assertHolidayOn(holidays, "GUAM_HISTORY_AND_CHAMORRO_HERITAGE_DAY", LocalDate.of(year, MARCH, 1).with(dayOfWeekInMonth(1, MONDAY)));
    assertHolidayOn(holidays, "MEMORIAL_DAY", LocalDate.of(year, MAY, 1).with(lastInMonth(MONDAY)));
    assertHolidayOn(holidays, "LABOUR_DAY", LocalDate.of(year, SEPTEMBER, 1).with(dayOfWeekInMonth(1, MONDAY)));
    assertHolidayOn(holidays, "THANKSGIVING", LocalDate.of(year, NOVEMBER, 1).with(dayOfWeekInMonth(4, THURSDAY)));
  }

  private static void assertHolidayOn(final Set<Holiday> holidays, final String propertyKey, final LocalDate date) {
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals(propertyKey))
      .extracting(Holiday::getDate)
      .containsExactly(date);
  }
}
