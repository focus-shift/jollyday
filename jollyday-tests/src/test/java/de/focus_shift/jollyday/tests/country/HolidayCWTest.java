package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.CURACAO;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayCWTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(CURACAO)
      // Arbeidsregeling Curaçao: only King's Day and Labour Day have a codified substitution
      // rule; every other fixed holiday below is observed on its literal date, weekend or not.
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      // Dutch royal tradition: moves BACKWARD to the preceding Saturday, not forward
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CURACAO_NATIONAL_FLAG_AND_ANTHEM_DAY", JULY, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      // Curaçao became an autonomous country within the Kingdom on 10 October 2010
      .hasFixedHoliday("CURACAO_DAY", OCTOBER, 10).validBetween(Year.of(2010), YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      // Listed by the government as "a number of hours", not a full day off, unlike every other entry
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("CARNIVAL_MONDAY", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(CURACAO));

    // King's Day: 27 April 2025 is a Sunday -> observed the preceding Saturday, 26 April 2025
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, APRIL, 26));
    assertThat(holidayDates(manager, Year.of(2025))).doesNotContain(LocalDate.of(2025, APRIL, 27));

    // Labour Day: 1 May 2022 is a Sunday -> observed the following Monday, 2 May 2022
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, MAY, 2));
    assertThat(holidayDates(manager, Year.of(2022))).doesNotContain(LocalDate.of(2022, MAY, 1));

    // Confirmed via the official 2023 government holiday table: New Year's Day (a Sunday),
    // Flag and Anthem Day (a Sunday), and New Year's Eve (a Sunday) are all observed on their
    // literal, unshifted dates that year - unlike King's Day and Labour Day, above
    assertThat(holidayDates(manager, Year.of(2023))).contains(
      LocalDate.of(2023, JANUARY, 1), LocalDate.of(2023, JULY, 2), LocalDate.of(2023, DECEMBER, 31));

    // Curaçao Day only exists from 2010 onward
    assertThat(holidayKeys(manager, Year.of(2009))).doesNotContain("CURACAO_DAY");
    assertThat(holidayKeys(manager, Year.of(2010))).contains("CURACAO_DAY");
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }

  private static Set<String> holidayKeys(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getPropertiesKey).collect(Collectors.toSet());
  }
}
