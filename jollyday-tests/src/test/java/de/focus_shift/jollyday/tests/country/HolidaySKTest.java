package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SLOWAKIA;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidaySKTest {

  @Test
  void ensuresHolidays() {
    assertFor(SLOWAKIA)
      .hasFixedHoliday("FOUNDATION", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1992))
        .validFrom(Year.of(1993))
      .and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .notValidBetween(Year.of(1800), Year.of(1885))
        .validFrom(Year.of(1886))
      .and()
      .hasFixedHoliday("VICTORY", MAY, 8).and()
      .hasFixedHoliday("CYRUS_METHODIUS", JULY, 5).and()
      .hasFixedHoliday("NATIONAL_UPRISING", AUGUST, 29).and()
      .hasFixedHoliday("CONSTITUTION_DAY", SEPTEMBER, 1)
        .validTo(Year.of(2024))
        .notValidBetween(Year.of(2025), Year.of(2500))
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", SEPTEMBER, 1, OBSERVANCE)
        .notValidBetween(Year.of(1900), Year.of(2024))
        .validFrom(Year.of(2025))
      .and()
      .hasFixedHoliday("VIRGIN_MARY", SEPTEMBER, 15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", NOVEMBER, 17).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
