package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.BENIN;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HolidayBJTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(BENIN)
      // Legal holidays (Law 90-019, art. 1)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NATIONAL_DAY", AUGUST, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ASCENSION_DAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()
      // Vodoun Festival: 10 January from 1998 (Law 97-031), then the second Friday of January
      // and the Thursday before it from 2025 (Law 2024-32, art. 3)
      .hasFixedHoliday("VODOUN_FESTIVAL", JANUARY, 10)
        .notValidBetween(YEAR_FROM, Year.of(1997))
        .validBetween(Year.of(1998), Year.of(2024)).and()
      .hasFixedWeekdayHoliday("VODOUN_FESTIVAL", SECOND, FRIDAY, JANUARY)
        .notValidBetween(YEAR_FROM, Year.of(1997))
        .validBetween(Year.of(2025), YEAR_TO).and()
      .hasRelativeToWeekdayInMonthHoliday("VODOUN_FESTIVAL", THURSDAY, BEFORE, SECOND, FRIDAY, JANUARY)
        .notValidBetween(YEAR_FROM, Year.of(1997))
        .validBetween(Year.of(2025), YEAR_TO).and()
      // National Days (Law 90-019, art. 3) are working days
      .hasFixedHoliday("REMEMBRANCE", JANUARY, 16, OBSERVANCE)
        .notValidBetween(YEAR_FROM, Year.of(1989))
        .validBetween(Year.of(1990), YEAR_TO).and()
      .hasFixedHoliday("PEOPLES_SOVEREIGNTY_DAY", FEBRUARY, 28, OBSERVANCE)
        .notValidBetween(YEAR_FROM, Year.of(1989))
        .validBetween(Year.of(1990), YEAR_TO).and()
      .hasFixedHoliday("WOMENS_DAY", MARCH, 8, OBSERVANCE)
        .notValidBetween(YEAR_FROM, Year.of(1989))
        .validBetween(Year.of(1990), YEAR_TO)
      .check();
  }

  @Test
  void ensuresThatDaysNotListedByLaw90019AreNoHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(BENIN));
    for (int year = YEAR_FROM.getValue(); year <= YEAR_TO.getValue(); year++) {
      final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));
      assertThat(holidays)
        .extracting(Holiday::getPropertiesKey)
        .doesNotContain("christian.EASTER", "ARMED_FORCES_DAY", "BOXING_DAY");
      assertThat(holidays)
        .filteredOn(holiday -> holiday.getPropertiesKey().equals("NATIONAL_DAY"))
        .extracting(Holiday::getDate)
        .containsExactly(LocalDate.of(year, AUGUST, 1));
    }
  }

  static Stream<Arguments> vodounFestivalDays() {
    return Stream.of(
      arguments(1997, List.of()),
      arguments(1998, List.of(LocalDate.of(1998, JANUARY, 10))),
      arguments(2024, List.of(LocalDate.of(2024, JANUARY, 10))),
      // days off declared by the government in 2025 and 2026
      arguments(2025, List.of(LocalDate.of(2025, JANUARY, 9), LocalDate.of(2025, JANUARY, 10))),
      arguments(2026, List.of(LocalDate.of(2026, JANUARY, 8), LocalDate.of(2026, JANUARY, 9))),
      arguments(2027, List.of(LocalDate.of(2027, JANUARY, 7), LocalDate.of(2027, JANUARY, 8)))
    );
  }

  @ParameterizedTest
  @MethodSource("vodounFestivalDays")
  void ensuresVodounFestivalDays(final int year, final List<LocalDate> expectedDays) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(BENIN));

    final Set<LocalDate> holidaysBetweenNewYearAndRemembranceDay = holidayManager
      .getHolidays(LocalDate.of(year, JANUARY, 2), LocalDate.of(year, JANUARY, 15)).stream()
      .map(Holiday::getDate)
      .collect(toSet());

    assertThat(holidaysBetweenNewYearAndRemembranceDay).containsExactlyInAnyOrderElementsOf(expectedDays);
  }
}
