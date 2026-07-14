package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TURKS_AND_CAICOS_ISLANDS;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayTCTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(TURKS_AND_CAICOS_ISLANDS)
      // Falls on a weekend -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Boxing Day is bumped to Tuesday instead of Monday when it would otherwise collide
      // with a Christmas Day substitute
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
      .and()
      // 2022 Platinum Jubilee: Queen's Birthday moved to Friday 3 June, extra holiday on Monday 6 June
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 3).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 6).validBetween(of(2022), of(2022)).and()
      .hasFixedWeekdayHoliday("COMMONWEALTH_DAY", SECOND, MONDAY, MARCH).and()
      // Renamed from National Heroes Day to JAGS McCartney Day in 2020; last Monday in May
      .hasFixedWeekdayHoliday("NATIONAL_HEROES_DAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2019))
        .notValidBetween(Year.of(2020), YEAR_TO)
      .and()
      .hasFixedWeekdayHoliday("JAGS_MCCARTNEY_DAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(2020))
        .notValidBetween(YEAR_FROM, Year.of(2019))
      .and()
      // Sovereign's official birthday: second Monday in June 2018-2021 and 2023-2024,
      // third Monday in June from 2025 (no reliable formula outside verified windows)
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE).validBetween(Year.of(2018), Year.of(2021)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validBetween(Year.of(2023), Year.of(2024)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", THIRD, MONDAY, JUNE).validFrom(Year.of(2025)).and()
      // New holiday introduced from 2026, marking the 1976 Constitution
      .hasFixedWeekdayHoliday("CONSTITUTION_DAY", LAST, MONDAY, AUGUST)
        .validFrom(Year.of(2026))
        .notValidBetween(YEAR_FROM, Year.of(2025))
      .and()
      // Moved from the fourth Friday in November to the Sunday before Constitution Day from 2026
      .hasFixedWeekdayHoliday("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", LAST, SUNDAY, AUGUST).validFrom(Year.of(2026)).and()
      .hasFixedWeekdayHoliday("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER).validBetween(Year.of(2014), Year.of(2025)).and()
      .hasFixedWeekdayHoliday("NATIONAL_YOUTH_DAY", LAST, FRIDAY, SEPTEMBER).and()
      // National Heritage Day replaced Columbus Day in 2014; second Monday in October
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER).validTo(Year.of(2013)).and()
      .hasFixedWeekdayHoliday("NATIONAL_HERITAGE_DAY", SECOND, MONDAY, OCTOBER).validFrom(Year.of(2014)).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
