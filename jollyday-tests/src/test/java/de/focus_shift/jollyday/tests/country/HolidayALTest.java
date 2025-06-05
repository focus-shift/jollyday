package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ALBANIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayALTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(ALBANIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .between(YEAR_FROM, YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .between(YEAR_FROM, YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .between(YEAR_FROM, YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("SPRING_DAY", MARCH, 14)
        .between(Year.of(2004), YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .between(YEAR_FROM, YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("MOTHER_TERESA", OCTOBER, 19)
        .between(Year.of(2003), YEAR_TO)
        .canBeShiftedFrom(SATURDAY, MONDAY)
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 28)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LIBERATION", NOVEMBER, 29)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .between(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("EASTER")
        .between(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY")
        .between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR")
        .between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
