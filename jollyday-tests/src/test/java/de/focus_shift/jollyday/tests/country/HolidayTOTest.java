package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.TONGA;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayTOTest {

  @Test
  void ensuresHolidays() {
    assertFor(TONGA)
      // New Year's Day (not mondayised)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      // Anzac Day (not mondayised)
      .hasFixedHoliday("ANZAC", APRIL, 25).and()
      // Emancipation Day - mondayised
      .hasFixedHoliday("EMANCIPATION_DAY", JUNE, 4)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // King Tupou VI Day - Sovereign's birthday (only shifted when falling on Sunday)
      .hasFixedHoliday("KING_TUPOU_VI_DAY", JULY, 4)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Crown Prince's Birthday - Heir's birthday (only shifted when falling on Sunday)
      .hasFixedHoliday("CROWN_PRINCE_BIRTHDAY", SEPTEMBER, 17)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Constitution Day - mondayised
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 4)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // King Tupou I Day - mondayised
      .hasFixedHoliday("KING_TUPOU_I_DAY", DECEMBER, 4)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Christmas Day (not mondayised)
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      // Boxing Day (not mondayised)
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Good Friday (not mondayised)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      // Easter Monday (not mondayised)
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
