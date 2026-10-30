package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.LATVIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayLVTest {

  @Test
  void ensuresHolidays() {
    assertFor(LATVIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 4).validTo(Year.of(2006)).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 4)
        .validFrom(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ICE_HOCKEY_BRONZE_MEDAL", MAY, 29).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("MIDSUMMER_EVE", JUNE, 23).and()
      .hasFixedHoliday("MIDSUMMER", JUNE, 24).and()
      .hasFixedHoliday("SONG_AND_DANCE_CELEBRATION", JULY, 9).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("SONG_AND_DANCE_CELEBRATION", JULY, 10).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("PAPAL_VISIT", SEPTEMBER, 24).validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 18).validTo(Year.of(2006)).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 18)
        .validFrom(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasFixedWeekdayHoliday("MOTHERS_DAY", SECOND, SUNDAY, MAY).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("PENTECOST")
      .check();
  }
}
