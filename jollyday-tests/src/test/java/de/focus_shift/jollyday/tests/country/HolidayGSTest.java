package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Year.of;

class HolidayGSTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("POSSESSION_DAY", JANUARY, 17)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // one-off UK-wide coronation holiday, also observed in SGSSI
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8).validBetween(of(2023), of(2023)).and()
      // does not follow a simple weekend-substitution rule (see Holidays_gs.xml); the confirmed
      // 2023 exception (Saturday 20 May observed Friday 19 May) is modeled explicitly, the base
      // rule (unshifted) applies otherwise
      .hasFixedHoliday("SHACKLETON_DAY", MAY, 20).validBetween(YEAR_FROM, of(2022)).and()
      .hasFixedHoliday("SHACKLETON_DAY", MAY, 19).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("SHACKLETON_DAY", MAY, 20).validBetween(of(2024), YEAR_TO).and()
      // one-off holiday for King Charles III's birthday, only observed in SGSSI in 2023
      .hasFixedHoliday("KINGS_BIRTHDAY", NOVEMBER, 14).validBetween(of(2023), of(2023)).and()
      .hasFixedHoliday("LIBERATION_DAY", APRIL, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // does not follow a simple weekend-substitution rule (see Holidays_gs.xml); the confirmed
      // 2025 (Saturday -> preceding Friday) and 2026 (Sunday -> following Monday) exceptions are
      // modeled explicitly, the base rule (unshifted) applies otherwise
      .hasFixedHoliday("MID_WINTER_DAY", JUNE, 21).validBetween(YEAR_FROM, of(2024)).and()
      .hasFixedHoliday("MID_WINTER_DAY", JUNE, 20).validBetween(of(2025), of(2025)).and()
      .hasFixedHoliday("MID_WINTER_DAY", JUNE, 22).validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("MID_WINTER_DAY", JUNE, 21).validBetween(of(2027), YEAR_TO).and()
      // replaced Toothfish Day (4 September) by 2023 at the latest; earliest year confirmed via a
      // primary-source SGSSI Gazette is used as validFrom rather than guessing an earlier year
      .hasFixedHoliday("ENVIRONMENT_DAY", OCTOBER, 30).validBetween(of(2023), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
