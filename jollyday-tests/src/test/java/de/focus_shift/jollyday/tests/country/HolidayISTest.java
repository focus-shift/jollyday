package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;

import static de.focus_shift.jollyday.core.HolidayCalendar.ICELAND;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayISTest {

  @Test
  void ensuresHolidays() {

    assertFor(ICELAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", FEBRUARY, 18).and()
      .hasFixedHoliday("BEER_DAY", MARCH, 1).and()
      .hasFixedHoliday("MAY_DAY", MAY, 1).and()
      .hasFixedHoliday("MOTHERS_DAY", MAY, 13).and()
      .hasFixedHoliday("NATIONAL_DAY", JUNE, 17).and()
      .hasFixedHoliday("JONSMESSA", JUNE, 24).and()
      .hasFixedHoliday("LANGUAGE_DAY", NOVEMBER, 16).and()
      .hasFixedHoliday("SELF_GOVERNANCE", DECEMBER, 1).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasFixedWeekdayHoliday("COMMERCE_DAY", FIRST, MONDAY, AUGUST).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasFixedWeekdayBetweenFixedHoliday("HUSBANDS_DAY", FRIDAY, MonthDay.of(JANUARY, 19), MonthDay.of(JANUARY, 25)).and()
      .hasFixedWeekdayRelativeToFixedHoliday("FIRST_DAY_SUMMER", FIRST, THURSDAY, AFTER, MonthDay.of(APRIL, 18))
      .check();
  }

}
