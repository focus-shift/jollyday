package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.POLAND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayPLTest {

  @Test
  void ensuresHolidays() {
    assertFor(POLAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY,1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY,6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY,1).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY,3).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST,15).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER,1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER,11).and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER,24)
        .notBetween(Year.of(1900), Year.of(2024))
        .between(Year.of(2025), Year.of(2500))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER,25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER,26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
