package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.CONGO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;

class HolidayCDTest {

  @Test
  void ensuresHolidays() {
    assertFor(CONGO)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("MARTYRS_DAY", JANUARY, 4).and()
      .hasFixedHoliday("LAURENT_DESIRE_KABILA_ASSASSINATION", JANUARY, 16).and()
      .hasFixedHoliday("PATRICE_LUMUMBA_ASSASSINATION", JANUARY, 17).and()
      .hasFixedHoliday("KIMBANGUS_DAY", APRIL, 6).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("LIBERATION", MAY, 17).validTo(Year.of(2018)).and()
      .hasFixedHoliday("ARMED_FORCES_DAY", MAY, 17).validFrom(Year.of(2019)).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 30).and()
      .hasFixedHoliday("PARENTS_DAY", AUGUST, 1).and()
      .hasFixedHoliday("CONGOLESE_GENOCIDE_DAY", AUGUST, 2).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
      .check();
  }
}
