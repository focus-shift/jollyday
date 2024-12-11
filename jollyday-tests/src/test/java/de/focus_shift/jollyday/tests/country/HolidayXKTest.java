package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.KOSOVO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;

class HolidayXKTest {

  @Test
  void ensuresHolidays() {
    assertFor(KOSOVO)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("DAY_OF_ASHKALI", FEBRUARY, 15).and()
      .hasFixedHoliday("DECLARATION_OF_INDEPENDENCE_DAY", FEBRUARY, 17).and()
      .hasFixedHoliday("VETERANS_DAY", MARCH, 6).and()
      .hasFixedHoliday("ROMA_DAY", MARCH, 8).and()
      .hasFixedHoliday("DAY_OF_THE_TURKS", APRIL, 23).and()
      .hasFixedHoliday("LABOR_DAY", MAY, 1).and()
      .hasFixedHoliday("DAY_OF_THE_GORANS", MAY, 6).and()
      .hasFixedHoliday("EUROPE_DAY", MAY, 9).and()
      .hasFixedHoliday("DAY_OF_PEACE", JUNE, 12).and()
      .hasFixedHoliday("CONSTITUTION_DAY", JUNE, 15).and()
      .hasFixedHoliday("DAY_OF_BOSNIAKS", SEPTEMBER, 28).and()
      .hasFixedHoliday("DAY_OF_ALBANIANS", NOVEMBER, 28).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}
