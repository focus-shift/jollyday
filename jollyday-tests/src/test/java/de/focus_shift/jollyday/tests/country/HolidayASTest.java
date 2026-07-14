package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.AMERICAN_SAMOA;
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
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayASTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(AMERICAN_SAMOA)
      // Falls on a weekend -> observed the previous Friday / following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // American Samoa Flag Day: commemorates the 1900 Deed of Cession of Tutuila
      .hasFixedHoliday("FLAG_DAY", APRIL, 17).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("JUNETEENTH", JUNE, 19).validFrom(Year.of(2021))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 4).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Manu'a Islands Cession Day: commemorates the 1904 Deed of Cession of the Manu'a
      // Islands, added to A.S.C.A. 1.0501 by Public Law 18-21 (1983)
      .hasFixedHoliday("MANUA_ISLANDS_CESSION_DAY", JULY, 16).validFrom(Year.of(1983))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("VETERANS_DAY", NOVEMBER, 11).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Not codified in 1.0501, but consistently granted to ASG employees alongside the federal calendar.
      .hasFixedWeekdayHoliday("MARTIN_LUTHER_KING", THIRD, MONDAY, JANUARY)
        .validFrom(Year.of(1986))
        .notValidBetween(YEAR_FROM, Year.of(1985))
      .and()
      // Fixed 22 February in 1.0501's text, but conforms to the federal third-Monday-in-February date since 1971.
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY).validFrom(Year.of(1971)).and()
      // Fixed 30 May in 1.0501's text, but conforms to the federal last-Monday-in-May date since 1971.
      .hasFixedWeekdayHoliday("MEMORIAL_DAY", LAST, MONDAY, MAY).validFrom(Year.of(1971)).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, SEPTEMBER).and()
      // Not codified in 1.0501; granted alongside the federal calendar.
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER).validFrom(Year.of(1971)).and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER).and()
      // White Sunday: a longstanding Samoan Christian tradition honouring children -
      // not substituted even though it always falls on a Sunday.
      .hasFixedWeekdayHoliday("WHITE_SUNDAY", SECOND, SUNDAY, OCTOBER).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }
}
