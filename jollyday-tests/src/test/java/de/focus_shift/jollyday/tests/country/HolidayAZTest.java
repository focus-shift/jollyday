package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.AZERBAIJAN;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAZTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(AZERBAIJAN)
      // New Year's Day (2 days)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      // Martyrs' Day - Black January 1990
      .hasFixedHoliday("MARTYRS_DAY", JANUARY, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      // Women's Day
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validBetween(YEAR_FROM, YEAR_TO).and()
      // Nowruz: two days until 2006, five days since 2007
      .hasFixedHoliday("NOWRUZ", MARCH, 20).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 21).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 22).validBetween(Year.of(2007), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2006)).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 23).validBetween(Year.of(2007), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2006)).and()
      .hasFixedHoliday("NOWRUZ", MARCH, 24).validBetween(Year.of(2007), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2006)).and()
      // Victory Day over Fascism
      .hasFixedHoliday("DAY_OF_VICTORY_OVER_FASCISM", MAY, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      // Independence Day
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 28).validBetween(YEAR_FROM, YEAR_TO).and()
      // National Salvation Day
      .hasFixedHoliday("NATIONAL_SALVATION_DAY", JUNE, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      // Azerbaijan Armed Forces Day
      .hasFixedHoliday("ARMED_FORCES_DAY", JUNE, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      // Non-working days under the Labour Code of 1999 until 2006
      .hasFixedHoliday("RESTORATION_OF_INDEPENDENCE", OCTOBER, 18).validBetween(Year.of(1999), Year.of(2006))
        .notValidBetween(YEAR_FROM, Year.of(1998)).notValidBetween(Year.of(2007), YEAR_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 12).validBetween(Year.of(1999), Year.of(2006))
        .notValidBetween(YEAR_FROM, Year.of(1998)).notValidBetween(Year.of(2007), YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_REVIVAL_DAY", NOVEMBER, 17).validBetween(Year.of(1999), Year.of(2006))
        .notValidBetween(YEAR_FROM, Year.of(1998)).notValidBetween(Year.of(2007), YEAR_TO).and()
      // Victory Day (2020 Nagorno-Karabakh war)
      .hasFixedHoliday("VICTORY_DAY", NOVEMBER, 8).validBetween(Year.of(2021), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2020)).and()
      // State Flag Day
      .hasFixedHoliday("STATE_FLAG_DAY", NOVEMBER, 9).validBetween(Year.of(2010), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2009)).and()
      // International Solidarity Day of Azerbaijanis
      .hasFixedHoliday("INTERNATIONAL_SOLIDARITY_DAY", DECEMBER, 31).validBetween(YEAR_FROM, YEAR_TO).and()
      // Eid al-Fitr (2 days)
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      // Eid al-Adha: one day until 2006, two days since 2007
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").validBetween(Year.of(2007), YEAR_TO).notValidBetween(YEAR_FROM, Year.of(2006))
      .check();
  }

  /**
   * Non-working days per the production calendars of the Ministry of Labour and Social Protection,
   * including the Cabinet of Ministers decisions on moving work and rest days and voting days.
   */
  static Stream<Arguments> productionCalendar() {
    return Stream.of(
      Arguments.of(2023, new String[]{
        "01-01", "01-02", "01-03", "01-04", "01-20", "03-08", "03-20", "03-21", "03-22", "03-23", "03-24",
        "04-21", "04-22", "04-24", "05-09", "05-28", "05-29", "06-15", "06-26", "06-27", "06-28", "06-29", "06-30",
        "11-08", "11-09", "11-10", "12-31"
      }),
      Arguments.of(2024, new String[]{
        "01-01", "01-02", "01-03", "01-04", "01-05", "01-20", "02-07", "03-08", "03-20", "03-21", "03-22", "03-23",
        "03-24", "03-25", "03-26", "04-10", "04-11", "04-12", "05-09", "05-28", "06-15", "06-16", "06-17", "06-18",
        "06-19", "06-26", "11-08", "11-09", "11-11", "11-12", "11-13", "12-30", "12-31"
      }),
      Arguments.of(2025, new String[]{
        "01-01", "01-02", "01-03", "01-20", "01-29", "03-08", "03-20", "03-21", "03-22", "03-23", "03-24", "03-25",
        "03-26", "03-27", "03-28", "03-30", "03-31", "05-09", "05-28", "06-06", "06-07", "06-09", "06-15", "06-16",
        "06-26", "06-27", "11-08", "11-09", "11-10", "11-11", "12-31"
      }),
      Arguments.of(2026, new String[]{
        "01-01", "01-02", "01-20", "03-08", "03-09", "03-20", "03-21", "03-22", "03-23", "03-24", "03-25", "03-26",
        "03-27", "03-30", "05-09", "05-11", "05-27", "05-28", "05-29", "06-15", "06-26", "11-08", "11-09", "11-10",
        "12-31"
      })
    );
  }

  @ParameterizedTest
  @MethodSource("productionCalendar")
  void ensuresNonWorkingDaysMatchProductionCalendar(final int year, final String[] expectedDates) {
    final Set<LocalDate> expected = Arrays.stream(expectedDates)
      .map(monthDay -> MonthDay.parse("--" + monthDay).atYear(year))
      .collect(toSet());

    final Set<LocalDate> actual = HolidayManager.getInstance(create(AZERBAIJAN)).getHolidays(Year.of(year)).stream()
      .map(Holiday::getDate)
      .collect(toSet());

    assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
  }
}
