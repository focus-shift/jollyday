package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.POLAND;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPLTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(POLAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY,1).and()
      .hasFixedHoliday("EPIPHANY", JANUARY,6)
        .validBetween(YEAR_FROM, Year.of(1960))
        .notValidBetween(Year.of(1961), Year.of(2010))
        .validBetween(Year.of(2011), YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY,1).and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY,3)
        .notValidBetween(YEAR_FROM, Year.of(1989))
        .validBetween(Year.of(1990), YEAR_TO)
      .and()
      .hasFixedHoliday("NATIONAL_DAY_OF_REBIRTH_OF_POLAND", JULY, 22)
        .notValidBetween(YEAR_FROM, Year.of(1944))
        .validBetween(Year.of(1945), Year.of(1989))
        .notValidBetween(Year.of(1990), YEAR_TO)
      .and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST,15)
        .validBetween(YEAR_FROM, Year.of(1960))
        .notValidBetween(Year.of(1961), Year.of(1988))
        .validBetween(Year.of(1989), YEAR_TO)
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER,1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER,11)
        .notValidBetween(YEAR_FROM, Year.of(1988))
        .validBetween(Year.of(1989), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER,24)
        .notValidBetween(YEAR_FROM, Year.of(2024))
        .validBetween(Year.of(2025), YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER,25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER,26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }

  /**
   * Days off according to Art. 1 of the act of 18 January 1951 (Dz.U. 1951 nr 4 poz. 28) as amended
   * in the respective year, see https://isap.sejm.gov.pl/isap.nsf/DocDetails.xsp?id=WDU19510040028
   */
  @ParameterizedTest
  @CsvSource({
    // 1951 act unchanged; the amendment of 1960 came into force on 24 November 1960
    "1960, NEW_YEAR EPIPHANY LABOUR_DAY NATIONAL_DAY_OF_REBIRTH_OF_POLAND ASSUMPTION_DAY ALL_SAINTS CHRISTMAS STEPHENS",
    // Epiphany and Assumption removed
    "1961, NEW_YEAR LABOUR_DAY NATIONAL_DAY_OF_REBIRTH_OF_POLAND ALL_SAINTS CHRISTMAS STEPHENS",
    "1988, NEW_YEAR LABOUR_DAY NATIONAL_DAY_OF_REBIRTH_OF_POLAND ALL_SAINTS CHRISTMAS STEPHENS",
    // Assumption and Independence Day added in May 1989
    "1989, NEW_YEAR LABOUR_DAY NATIONAL_DAY_OF_REBIRTH_OF_POLAND ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS STEPHENS",
    // 22 July abolished and 3 May restored in April 1990
    "1990, NEW_YEAR LABOUR_DAY CONSTITUTION_DAY ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS STEPHENS",
    "2010, NEW_YEAR LABOUR_DAY CONSTITUTION_DAY ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS STEPHENS",
    // Epiphany restored from 1 January 2011
    "2011, NEW_YEAR EPIPHANY LABOUR_DAY CONSTITUTION_DAY ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS STEPHENS",
    "2024, NEW_YEAR EPIPHANY LABOUR_DAY CONSTITUTION_DAY ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS STEPHENS",
    // Christmas Eve added from 1 February 2025
    "2025, NEW_YEAR EPIPHANY LABOUR_DAY CONSTITUTION_DAY ASSUMPTION_DAY ALL_SAINTS INDEPENDENCE_DAY CHRISTMAS_EVE CHRISTMAS STEPHENS",
  })
  void ensuresDaysOffOfTheYear(final int year, final String fixedHolidays) {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(POLAND)).getHolidays(Year.of(year));

    final List<String> expected = new ArrayList<>(List.of(fixedHolidays.split(" ")));
    expected.addAll(List.of("christian.EASTER", "christian.EASTER_MONDAY", "christian.PENTECOST", "christian.CORPUS_CHRISTI"));

    assertThat(holidays)
      .extracting(Holiday::getPropertiesKey)
      .containsExactlyInAnyOrderElementsOf(expected);
  }
}
