package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.MARSHALL_ISLANDS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayMHTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(MARSHALL_ISLANDS)
      // Public Holidays Act 1988, 1 MIRC Ch.9, §902: Saturday -> preceding Friday, Sunday -> following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("NUCLEAR_VICTIMS_REMEMBRANCE_DAY", MARCH, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("PRESIDENTS_DAY", NOVEMBER, 17)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedWeekdayHoliday("FISHERMENS_DAY", FIRST, FRIDAY, JULY).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, FRIDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("MANIT_DAY", LAST, FRIDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("GOSPEL_DAY", FIRST, FRIDAY, DECEMBER)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(MARSHALL_ISLANDS));

    // New Year's Day: 1 Jan 2022 is a Saturday -> observed the preceding Friday, 31 Dec 2021,
    // which is returned when querying year 2022 (the year the un-shifted 1 January falls in)
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2021, DECEMBER, 31));
    assertThat(holidayDates(manager, Year.of(2022))).doesNotContain(LocalDate.of(2022, JANUARY, 1));
    // 1 Jan 2023 is a Sunday -> observed the following Monday, 2 Jan 2023
    assertThat(holidayDates(manager, Year.of(2023))).contains(LocalDate.of(2023, JANUARY, 2));

    // Nuclear Victims Remembrance Day: 1 Mar 2025 is a Saturday -> observed Friday 28 Feb 2025
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, java.time.Month.FEBRUARY, 28));
    assertThat(holidayDates(manager, Year.of(2025))).doesNotContain(LocalDate.of(2025, MARCH, 1));
    // 1 Mar 2026 is a Sunday -> observed the following Monday, 2 Mar 2026
    assertThat(holidayDates(manager, Year.of(2026))).contains(LocalDate.of(2026, MARCH, 2));

    // Presidents' Day: 17 Nov 2024 is a Sunday -> observed the following Monday, 18 Nov 2024
    assertThat(holidayDates(manager, Year.of(2024))).contains(LocalDate.of(2024, NOVEMBER, 18));

    // Christmas: 25 Dec 2022 is a Sunday -> observed the following Monday, 26 Dec 2022
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, DECEMBER, 26));
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }
}
