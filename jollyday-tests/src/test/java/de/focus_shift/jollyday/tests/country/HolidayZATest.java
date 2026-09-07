package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.SOUTH_AFRICA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;

class HolidayZATest {
  @Test
  void ensuresHolidays() {
    assertFor(SOUTH_AFRICA)
      .hasFixedHoliday("ELECTION_DAY", JUNE, 2)
        .validBetween(of(1999), of(1999)).and()
      .hasFixedHoliday("ELECTION_DAY", APRIL, 14)
        .validBetween(of(2004), of(2004)).and()
      .hasFixedHoliday("ELECTION_DAY", MARCH, 1)
        .validBetween(of(2006), of(2006)).and()
      .hasFixedHoliday("ELECTION_DAY", APRIL, 22)
        .validBetween(of(2009), of(2009)).and()
      .hasFixedHoliday("ELECTION_DAY", MAY, 18)
        .validBetween(of(2011), of(2011)).and()
      .hasFixedHoliday("ELECTION_DAY", MAY, 7)
        .validBetween(of(2014), of(2014)).and()
      .hasFixedHoliday("ELECTION_DAY", AUGUST, 3)
        .validBetween(of(2016), of(2016)).and()
      .hasFixedHoliday("ELECTION_DAY", MAY, 8)
        .validBetween(of(2019), of(2019)).and()
      .hasFixedHoliday("ELECTION_DAY", NOVEMBER, 1)
        .validBetween(of(2021), of(2021)).and()
      .hasFixedHoliday("ELECTION_DAY", MAY, 29)
        .validBetween(of(2024), of(2024)).and()
      .hasFixedHoliday("ELECTION_DAY", NOVEMBER, 4)
        .validBetween(of(2026), of(2026)).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("HUMAN_RIGHTS", MARCH, 21)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("RIEBEECK", APRIL, 6)
        .validBetween(of(1952), of(1974)).and()
      .hasFixedHoliday("FOUNDATION", APRIL, 6)
        .validBetween(of(1980), of(1994)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(of(1990), of(2500)).and()
      .hasFixedHoliday("EMPIRE", MAY, 24)
        .validBetween(of(1910), of(1951)).and()
      .hasFixedHoliday("REPUBLIC_DAY", MAY, 31)
        .validBetween(of(1910), of(1993)).and()
      .hasFixedHoliday("FREEDOM", APRIL, 27)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validBetween(of(1990), of(2500))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("YOUTH", JUNE, 16)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", AUGUST, 9)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("HERITAGE", SEPTEMBER, 24)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("KRUGER", OCTOBER, 10)
        .validBetween(of(1952), of(1993))
      .and()
      .hasFixedHoliday("GOODWILL", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
        .validBetween(of(1910), of(1993))
      .check();
  }
}
