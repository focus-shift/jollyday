package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.COLOMBIA;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.NOVEMBER;

class HolidayCOTest {

  @Test
  void ensuresHolidays() {
    assertFor(COLOMBIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6)
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19)
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29)
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 20).and()
      .hasFixedHoliday("BOYACA", AUGUST, 7).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15)
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("CARTAGENA", NOVEMBER, 11)
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasChristianHoliday("CORPUS_CHRISTI")
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .and()
      .hasChristianHoliday("SACRED_HEART")
        .canBeShiftedFrom(TUESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(WEDNESDAY, NEXT, MONDAY)
        .canBeShiftedFrom(THURSDAY, NEXT, MONDAY)
        .canBeShiftedFrom(FRIDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SATURDAY, NEXT, MONDAY)
        .canBeShiftedFrom(SUNDAY, NEXT, MONDAY)
      .check();
  }
}
