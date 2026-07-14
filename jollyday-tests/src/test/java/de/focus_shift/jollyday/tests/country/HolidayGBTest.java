package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_KINGDOM;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayGBTest {

  @Test
  void ensuresHolidays() {
    assertFor(UNITED_KINGDOM)
      // New Year's Day did not become a bank holiday in England, Wales and Northern Ireland until 1 January 1974.
      // WARNING: for New Year's Day and 2nd January in Scotland the official government websites contradict
      // themselves, and it is therefore unclear when these really are. The current ruleset is chosen for
      // reasons of most simple code.
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()

      // Christmas Day / Boxing Day moving rules changed over the years (Scotland's substitute-day divergence)
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validTo(Year.of(2010))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2011), Year.of(2011))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(2012))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validTo(Year.of(2010))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(Year.of(2011), Year.of(2011))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validFrom(Year.of(2012))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()

      // one-off special bank holidays
      .hasFixedHoliday("ROYAL_WEDDING", APRIL, 29)
        .validBetween(Year.of(2011), Year.of(2011))
      .and()
      .hasFixedHoliday("EARLY_MAY_BANK_HOLIDAY", MAY, 8)
        .validBetween(Year.of(1995), Year.of(1995))
      .and()
      .hasFixedHoliday("EARLY_MAY_BANK_HOLIDAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 4)
        .validBetween(Year.of(2012), Year.of(2012))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_DIAMOND_JUBILEE", JUNE, 5)
        .validBetween(Year.of(2012), Year.of(2012))
      .and()
      .hasFixedHoliday("QUEENS_STATE_FUNERAL", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()

      // Early May bank holiday: first Monday of May, with historical gaps for the one-off overrides above
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validBetween(Year.of(1900), Year.of(1994))
      .and()
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validBetween(Year.of(1996), Year.of(2019))
      .and()
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validFrom(Year.of(2021))
      .and()

      // Spring bank holiday: last Monday of May, except where moved (2012, 2022 - see one-off overrides above)
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2011))
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validBetween(Year.of(2013), Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(2023))
      .and()

      .hasChristianHoliday("GOOD_FRIDAY").and()

      // England
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .inSubdivision("eng")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .inSubdivision("eng")
      .and()

      // Wales (always the same as England)
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .inSubdivision("wls")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .inSubdivision("wls")
      .and()

      // Scotland
      // WARNING: for New Year's Day and 2nd January in Scotland the official government websites contradict
      // themselves, and it is therefore unclear when these really are. The current ruleset is chosen for
      // reasons of most simple code.
      .hasFixedHoliday("2ND_JANUARY", JANUARY, 2)
        .inSubdivision("sct")
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("WORLD_CUP_BANK_HOLIDAY", JUNE, 15)
        .inSubdivision("sct")
        .validBetween(Year.of(2026), Year.of(2026))
      .and()
      .hasFixedHoliday("ST_ANDREW", NOVEMBER, 30)
        .inSubdivision("sct")
        .validFrom(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", FIRST, MONDAY, AUGUST)
        .inSubdivision("sct")
      .and()

      // Northern Ireland
      .hasFixedHoliday("ST_PATRICK", MARCH, 17)
        .inSubdivision("nir")
        .validFrom(Year.of(1903))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BATTLE_BOYNE", JULY, 12)
        .inSubdivision("nir")
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .inSubdivision("nir")
      .and()
      .hasChristianHoliday("EASTER_MONDAY")
        .inSubdivision("nir")

      .check();
  }
}
