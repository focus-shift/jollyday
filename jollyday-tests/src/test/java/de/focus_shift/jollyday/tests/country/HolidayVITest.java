package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.US_VIRGIN_ISLANDS;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayVITest {

  @Test
  void ensuresHolidays() {
    assertFor(US_VIRGIN_ISLANDS)
      // Fixed date holidays that are also U.S. federal holidays follow the federal
      // Saturday/Sunday mondanisation rule (1 V.I.C. § 171 / 5 U.S.C. § 6103).
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("JUNETEENTH", JUNE, 19)
        .validFrom(Year.of(2023))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 4)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("VETERANS_DAY", NOVEMBER, 11)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Fixed date holidays that only exist locally are not moved when they fall on a weekend.
      .hasFixedHoliday("THREE_KINGS_DAY", JANUARY, 6).and()
      .hasFixedHoliday("TRANSFER_DAY", MARCH, 31).and()
      .hasFixedHoliday("EMANCIPATION_DAY", JULY, 3).and()
      .hasFixedHoliday("D_HAMILTON_JACKSON_DAY", NOVEMBER, 1).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      // Weekday-based and Easter-relative holidays never need a weekend shift.
      .hasFixedWeekdayHoliday("MARTIN_LUTHER_KING", THIRD, MONDAY, JANUARY).and()
      .hasFixedWeekdayHoliday("PRESIDENTS_DAY", THIRD, MONDAY, FEBRUARY).and()
      .hasFixedWeekdayHoliday("MEMORIAL_DAY", LAST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, SEPTEMBER).and()
      .hasFixedWeekdayHoliday("PUERTO_RICO_FRIENDSHIP_DAY", SECOND, MONDAY, OCTOBER).and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER).and()
      .hasChristianHoliday("MAUNDY_THURSDAY")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
