package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.ARUBA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAWTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(ARUBA)
      // AV 2013 Art. 22: only King's Day and Labour Day have a codified substitution rule;
      // every other fixed holiday below is observed on its literal date, weekend or not.
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BETICO_CROES_DAY", JANUARY, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ARUBA_NATIONAL_ANTHEM_AND_FLAG_DAY", MARCH, 18).validBetween(YEAR_FROM, YEAR_TO).and()
      // Dutch royal tradition: moves BACKWARD to the preceding Saturday, not forward
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .canBeMovedFrom(SUNDAY, PREVIOUS, SATURDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("CARNIVAL_MONDAY", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(ARUBA));

    // King's Day: 27 April 2014 and 27 April 2025 are both Sundays -> observed the preceding
    // Saturday (26 April), confirmed via the government's first Koningsdag after
    // Willem-Alexander's accession (2014) and an explicit DAO announcement citing AV 2013
    // Art. 22 (2025).
    assertThat(holidayDates(manager, Year.of(2014))).contains(LocalDate.of(2014, APRIL, 26));
    assertThat(holidayDates(manager, Year.of(2014))).doesNotContain(LocalDate.of(2014, APRIL, 27));
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, APRIL, 26));
    assertThat(holidayDates(manager, Year.of(2025))).doesNotContain(LocalDate.of(2025, APRIL, 27));

    // King's Day: 27 April 2019 is a Saturday already -> observed unmoved
    assertThat(holidayDates(manager, Year.of(2019))).contains(LocalDate.of(2019, APRIL, 27));

    // Labour Day: 1 May 2022 is a Sunday -> observed the following Monday, 2 May 2022
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, MAY, 2));
    assertThat(holidayDates(manager, Year.of(2022))).doesNotContain(LocalDate.of(2022, MAY, 1));

    // Labour Day: 1 May 2021 is a Saturday already -> observed unmoved
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, MAY, 1));

    // Confirmed via official government holiday tables: New Year's Day (a Sunday in 2023) and
    // Aruba National Anthem and Flag Day (a Sunday in 2018) are both observed on their literal,
    // unshifted dates - unlike King's Day and Labour Day, above.
    assertThat(holidayDates(manager, Year.of(2023))).contains(LocalDate.of(2023, JANUARY, 1));
    assertThat(holidayDates(manager, Year.of(2018))).contains(LocalDate.of(2018, MARCH, 18));
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }
}
