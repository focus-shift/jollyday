package de.focus_shift.jollyday.tests;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.HAITI;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HTHolidayTest {

  @Test
  void ensuresHolidays() {
    assertFor(HAITI)
      // Fixed date holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ANCESTRY_DAY", JANUARY, 2).and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LABOUR_AND_AGRICULTURE_DAY", MAY, 1).and()
      .hasFixedHoliday("FLAG_AND_UNIVERSITIES_DAY", MAY, 18).and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("DESSALINES_DAY", OCTOBER, 17).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("BATTLE_OF_VERTIERES_DAY", NOVEMBER, 18).and()
      .hasFixedHoliday("DISCOVERY_DAY", DECEMBER, 5).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("ASH_WEDNESDAY").and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }
}
