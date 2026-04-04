package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import static de.focus_shift.jollyday.core.HolidayCalendar.EL_SALVADOR;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidaySVTest {

  @Test
  void ensuresHolidays() {
    assertFor(EL_SALVADOR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("DAY_OF_THE_CROSS", MAY, 3).and()
      .hasFixedHoliday("SOLDIERS_DAY", MAY, 7).and()
      .hasFixedHoliday("MOTHERS_DAY", MAY, 10).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 1).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 2).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 3).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 4).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 5).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 6).and()
      .hasFixedHoliday("AUGUST_FESTIVALS", AUGUST, 7).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 15).and()
      .hasFixedHoliday("CHILDRENS_DAY", OCTOBER, 1).and()
      .hasFixedHoliday("ETHNIC_PRIDE_DAY", OCTOBER, 12).and()
      .hasFixedHoliday("DAY_OF_THE_DEAD", NOVEMBER, 2).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 7).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 8).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 9).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 10).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 11).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 12).and()
      .hasFixedHoliday("NATIONAL_PUPUSA_FESTIVAL", NOVEMBER, 13).and()
      .hasFixedHoliday("DAY_OF_THE_QUEEN_OF_PEACE", NOVEMBER, 21).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER")
      .check();
  }
}
