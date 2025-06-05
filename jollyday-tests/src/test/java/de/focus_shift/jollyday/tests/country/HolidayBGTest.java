package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.BULGARIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayBGTest {

  @Test
  void ensuresHolidays() {
    assertFor(BULGARIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LIBERATION", MARCH, 3)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ST_GEORGE", MAY, 6)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("EDU_CULTURE", MAY, 24)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("UNIFICATION", SEPTEMBER, 6)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 22)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NATIONAL_DAY", NOVEMBER, 1)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24)
        .canBeShiftedFrom(SUNDAY, WEDNESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeShiftedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
