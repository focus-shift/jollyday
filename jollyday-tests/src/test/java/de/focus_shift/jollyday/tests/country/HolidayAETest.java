package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_ARAB_EMIRATES;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertFixed;
import static de.focus_shift.jollyday.tests.HolidayChecker.assertIslamic;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

class HolidayAETest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFixed(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, JANUARY, 1, "NEW_YEAR");
    assertFixed(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, DECEMBER, 1, "AE_COMMEMORATION_DAY");
    assertFixed(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, DECEMBER, 2, "NATIONAL_DAY");
    assertFixed(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, DECEMBER, 3, "NATIONAL_DAY");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "RAMADAN_END");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_AL_FITR");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_AL_FITR_2");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_AL_FITR_3");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ARAFAAT");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_UL_ADHA");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_UL_ADHA_2");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "ID_UL_ADHA_3");
    assertIslamic(UNITED_ARAB_EMIRATES, YEAR_FROM, YEAR_TO, "NEWYEAR");
  }
}
