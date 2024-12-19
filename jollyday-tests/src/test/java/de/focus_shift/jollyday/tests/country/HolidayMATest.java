package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.MOROCCO;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;

class HolidayMATest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(MOROCCO)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("PROCLAMATION_OF_INDEPENDENCE", JANUARY, 11).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMAZIGH_NEW_YEAR", JANUARY, 14)
        .notBetween(YEAR_FROM, Year.of(2023))
        .between(Year.of(2024), YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ENTHRONEMENT", JULY, 30).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ZIKRA_OUED_ED_DAHAB", AUGUST, 14).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("REVOLUTION_OF_THE_KING_AND_THE_PEOPLE", AUGUST, 20).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("YOUTH", AUGUST, 21).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("GREEN_MARCH", NOVEMBER, 6).between(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 18).between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("NEWYEAR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").between(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").between(YEAR_FROM, YEAR_TO)
      .check();
  }
}
