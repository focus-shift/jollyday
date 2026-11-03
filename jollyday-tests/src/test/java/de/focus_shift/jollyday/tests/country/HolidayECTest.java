package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ECUADOR;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayECTest {

  private static final Year LAST_YEAR_WITHOUT_MOVES = Year.of(2016);
  private static final Year FIRST_YEAR_WITH_MOVES = Year.of(2017);

  @Test
  void ensuresHolidays() {
    assertFor(ECUADOR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("PICHINCHA", MAY, 24).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("PICHINCHA", MAY, 24)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEP_QUITO", AUGUST, 10).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("INDEP_QUITO", AUGUST, 10)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEP_GUYAQUIL", OCTOBER, 9).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("INDEP_GUYAQUIL", OCTOBER, 9)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("INDEP_CUENCA", NOVEMBER, 3).validTo(LAST_YEAR_WITHOUT_MOVES).and()
      .hasFixedHoliday("INDEP_CUENCA", NOVEMBER, 3)
        .validFrom(FIRST_YEAR_WITH_MOVES)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, THURSDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("SHROVE_MONDAY").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  @ParameterizedTest
  @CsvSource({
    // year, 2 November (ALL_SOULS), 3 November (INDEP_CUENCA) per Disposición General Primera of RO Supl. 906
    "2017, 2017-11-02, 2017-11-03",
    "2018, 2018-11-02, 2018-11-01",
    "2019, 2019-11-01, 2019-11-04",
    "2020, 2020-11-02, 2020-11-03",
    "2021, 2021-11-02, 2021-11-01",
    "2022, 2022-11-04, 2022-11-03",
    "2023, 2023-11-02, 2023-11-03",
    "2024, 2024-11-01, 2024-11-04",
    "2025, 2025-11-04, 2025-11-03",
    "2026, 2026-11-02, 2026-11-03",
    "2027, 2027-11-02, 2027-11-01",
    "2028, 2028-11-02, 2028-11-03",
    "2029, 2029-11-02, 2029-11-01",
    "2030, 2030-11-01, 2030-11-04"
  })
  void ensuresConsecutiveNovemberHolidaysNeverFallOnTheSameDay(final int year, final LocalDate allSouls, final LocalDate independenceOfCuenca) {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(ECUADOR)).getHolidays(Year.of(year));

    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("ALL_SOULS"))
      .extracting(Holiday::getDate)
      .containsExactly(allSouls);
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("INDEP_CUENCA"))
      .extracting(Holiday::getDate)
      .containsExactly(independenceOfCuenca);
    assertThat(allSouls).isNotEqualTo(independenceOfCuenca);
  }

  @ParameterizedTest
  @CsvSource({
    "2022-05-02", "2022-05-23", "2022-08-12", "2022-10-10",
    "2023-05-26", "2023-08-11",
    "2024-05-03", "2024-08-09", "2024-10-11",
    "2025-05-02", "2025-05-23", "2025-08-11", "2025-10-10",
    "2026-05-25",
    "2027-04-30", "2027-08-09", "2027-10-08",
    // Carnival Monday and Tuesday
    "2025-03-03", "2025-03-04", "2026-02-16", "2026-02-17", "2027-02-08", "2027-02-09"
  })
  void ensuresMovedHolidaysAndCarnival(final LocalDate date) {
    assertThat(HolidayManager.getInstance(create(ECUADOR)).isHoliday(date)).isTrue();
  }

  @Test
  void ensuresDaysNotListedInArticle65AreNoHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ECUADOR));
    for (int year = 1900; year <= 2100; year++) {
      assertThat(holidayManager.getHolidays(Year.of(year)))
        .extracting(Holiday::getPropertiesKey)
        .doesNotContain("BOLIVAR", "FOUND_QUITO", "NEW_YEARS_EVE", "christian.MAUNDY_THURSDAY");
      assertThat(holidayManager.isHoliday(LocalDate.of(year, JULY, 24))).isFalse();
    }
  }
}
