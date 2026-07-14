package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANGUILLA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Year.of;

class HolidayAITest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(ANGUILLA)
      // Public Holidays Regulations, R.R.A. P130-1: falls on Sat/Sun -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("JAMES_RONALD_WEBSTER_DAY", MARCH, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2010), YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("ANGUILLA_DAY", MAY, 30)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // 2022 Platinum Jubilee one-off; 2026 breaks the "third Monday" pattern; no formula could be
      // confirmed for 2023 (per the official circular) or 2027 onward, so both are left unmodeled
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 3).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 22).validBetween(of(2026), of(2026)).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validTo(of(2021))
        .notValidBetween(of(2023), of(2023))
      .and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(of(2024), of(2025))
        .notValidBetween(of(2023), of(2023))
        .notValidBetween(of(2027), of(2027))
      .and()
      .hasFixedWeekdayHoliday("AUGUST_MONDAY", FIRST, MONDAY, AUGUST).and()
      .hasFixedWeekdayRelativeToFixedHoliday("AUGUST_THURSDAY", FIRST, THURSDAY, AFTER, MonthDay.of(AUGUST, 3)).and()
      .hasFixedWeekdayRelativeToFixedHoliday("CONSTITUTION_DAY", FIRST, FRIDAY, AFTER, MonthDay.of(AUGUST, 4)).and()
      // The only Anguilla holiday that moves BACKWARD to the preceding Friday when it falls on a weekend
      // Renamed from Separation Day to National Heroes and Heroines Day from 2011
      .hasFixedHoliday("NATIONAL_HEROES_AND_HEROINES_DAY", DECEMBER, 19)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, PREVIOUS, FRIDAY)
        .validBetween(Year.of(2011), YEAR_TO)
        .notValidBetween(YEAR_FROM, Year.of(2010))
      .and()
      .hasFixedHoliday("SEPARATION", DECEMBER, 19)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, PREVIOUS, FRIDAY)
        .validBetween(YEAR_FROM, Year.of(2010))
        .notValidBetween(Year.of(2011), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
