package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.MonthDay;

import static de.focus_shift.jollyday.core.HolidayCalendar.CANADA;
import static de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;

class HolidayCATest {

  @Test
  void ensuresHolidays() {
    assertFor(CANADA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("CANADA_DAY", JULY, 1)
        .canBeMovedFrom(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()

      /* Ontario */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("on").and()

      /* Yukon */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("yt").and()

      /* Quebec */
      .hasRelativeToFixedHoliday("PATRIOT", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("qc").and()

      /* Nova Scotia */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25), BANK_HOLIDAY).inSubdivision("ns").and()

      /* New Brunswick */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25), BANK_HOLIDAY).inSubdivision("nb").and()

      /* Manitoba */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("mb").and()

      /* Northwest Territories */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("nt").and()

      /* Nunavut */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("nu").and()

      /* British Columbia */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("bc").and()

      /* Prince Edward Island */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25), BANK_HOLIDAY).inSubdivision("pe").and()

      /* Saskatchewan */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("sk").and()

      /* Alberta */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25)).inSubdivision("ab").and()

      /* Newfoundland and Labrador */
      .hasRelativeToFixedHoliday("VICTORIA_DAY", MONDAY, BEFORE, MonthDay.of(MAY, 25), BANK_HOLIDAY).inSubdivision("nl")

      .check();
  }
}

