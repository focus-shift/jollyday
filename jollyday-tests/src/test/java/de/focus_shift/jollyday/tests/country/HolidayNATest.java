package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.NAMIBIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Year.of;

class HolidayNATest {
  @Test
  void ensuresHolidays() {
    assertFor(NAMIBIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MARCH, 21)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CASSINGA", MAY, 4)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("AFRICA_DAY", MAY, 25)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("GENOCIDE_REMEMBRANCE", MAY, 28)
        .validFrom(of(2016))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("HEROES", AUGUST, 26)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("HUMAN_RIGHTS", DECEMBER, 10)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("FAMILY_DAY", DECEMBER, 26)
        .validFrom(of(1990))
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY")
      .check();
  }
}
