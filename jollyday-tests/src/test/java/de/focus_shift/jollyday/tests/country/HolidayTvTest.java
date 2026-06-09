package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.TUVALU;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayTvTest {

  @Test
  void ensuresHolidays() {
    assertFor(TUVALU)
      // Fixed date national holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("TUVALU_DAY", OCTOBER, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Moveable Christian holidays
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      // Fixed weekday holidays (tested by specific dates for specific years)
      // Commonwealth Day - Second Monday of March
      // 2024: March 11, 2025: March 10, 2026: March 9
      .hasFixedHoliday("COMMONWEALTH_DAY", MARCH, 11).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("COMMONWEALTH_DAY", MARCH, 10).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("COMMONWEALTH_DAY", MARCH, 9).validBetween(Year.of(2026), Year.of(2026)).and()
      // Gospel Day - Second Monday of May
      // 2024: May 13, 2025: May 12, 2026: May 11
      .hasFixedHoliday("GOSPEL_DAY", MAY, 13).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("GOSPEL_DAY", MAY, 12).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("GOSPEL_DAY", MAY, 11).validBetween(Year.of(2026), Year.of(2026)).and()
      // King's Official Birthday - Second Saturday of June
      // 2024: June 8, 2025: June 14, 2026: June 13
      .hasFixedHoliday("KINGS_OFFICIAL_BIRTHDAY", JUNE, 8).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("KINGS_OFFICIAL_BIRTHDAY", JUNE, 14).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("KINGS_OFFICIAL_BIRTHDAY", JUNE, 13).validBetween(Year.of(2026), Year.of(2026)).and()
      // National Children's Day - First Monday of August
      // 2024: August 5, 2025: August 4, 2026: August 3
      .hasFixedHoliday("NATIONAL_CHILDRENS_DAY", AUGUST, 5).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("NATIONAL_CHILDRENS_DAY", AUGUST, 4).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("NATIONAL_CHILDRENS_DAY", AUGUST, 3).validBetween(Year.of(2026), Year.of(2026)).and()
      // Heir to Throne's Birthday - Second Monday of November
      // 2024: November 11, 2025: November 10, 2026: November 9
      .hasFixedHoliday("HEIR_TO_THRONES_BIRTHDAY", NOVEMBER, 11).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("HEIR_TO_THRONES_BIRTHDAY", NOVEMBER, 10).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("HEIR_TO_THRONES_BIRTHDAY", NOVEMBER, 9).validBetween(Year.of(2026), Year.of(2026))
      .check();
  }
}
