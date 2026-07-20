package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;

import static de.focus_shift.jollyday.core.HolidayCalendar.ALAND_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayAXTest {

  @Test
  void ensuresHolidays() {
    assertFor(ALAND_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("SELF_GOVERNANCE", JUNE, 9).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 6).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_SUNDAY").and()
      .hasFixedWeekdayBetweenFixedHoliday("MIDSUMMER_EVE", FRIDAY, MonthDay.of(JUNE, 19), MonthDay.of(JUNE, 25)).and()
      .hasFixedWeekdayBetweenFixedHoliday("MIDSUMMER", SATURDAY, MonthDay.of(JUNE, 20), MonthDay.of(JUNE, 26)).and()
      .hasFixedWeekdayBetweenFixedHoliday("ALL_SAINTS", SATURDAY, MonthDay.of(OCTOBER, 31), MonthDay.of(NOVEMBER, 6))
      .check();
  }
}
