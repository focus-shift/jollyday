package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ZIMBABWE;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;

class HolidayZWTest {

  @Test
  void ensuresHolidays() {
    assertFor(ZIMBABWE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
         .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ROBERT_GABRIEL_MUGABE_NATIONAL_YOUTH_DAY", FEBRUARY, 21)
        .validFrom(Year.of(2017))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", APRIL, 18)
        .validFrom(Year.of(1980))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("MOTHERS_DAY", MAY, 10, OBSERVANCE).and()
      .hasFixedHoliday("AFRICA_DAY", MAY, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("FATHERS_DAY", JUNE, 21, OBSERVANCE).and()
      .hasFixedHoliday("MUNHUMUTAPA_DAY", SEPTEMBER, 15)
        .validFrom(Year.of(2025))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NATIONAL_UNITY_DAY", DECEMBER, 22)
        .validFrom(Year.of(1997))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER", OBSERVANCE).and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }
}
