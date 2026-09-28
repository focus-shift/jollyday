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

import static de.focus_shift.jollyday.core.HolidayCalendar.KOSOVO;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HolidayXKTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(KOSOVO)
      // Official holidays (Law No. 03/L-064, Art. 2), moved to the next working day if on a weekend (Art. 4)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("ORTHODOX_CHRISTMAS", JANUARY, 7).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("DECLARATION_OF_INDEPENDENCE_DAY", FEBRUARY, 17).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", APRIL, 9)
        .notValidBetween(YEAR_FROM, Year.of(2008))
        .validBetween(Year.of(2009), Year.of(2022))
        .notValidBetween(Year.of(2023), Year.of(2023))
        .validBetween(Year.of(2024), YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // 2023: Sunday 9 April was followed by Catholic Easter Monday
      .hasFixedHoliday("CONSTITUTION_DAY", APRIL, 9)
        .validBetween(Year.of(2023), Year.of(2023))
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("EUROPE_DAY", MAY, 9).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("ORTHODOX_EASTER_MONDAY", true).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO).and()
      // Memorial days (Law No. 03/L-064, Art. 5.5) are working days
      .hasFixedHoliday("DAY_OF_ASHKALI", FEBRUARY, 15, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("VETERANS_DAY", MARCH, 6, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ROMA_DAY", APRIL, 8, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_THE_TURKS", APRIL, 23, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_THE_GORANS", MAY, 6, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_PEACE", JUNE, 12, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_BOSNIAKS", SEPTEMBER, 28, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("DAY_OF_ALBANIANS", NOVEMBER, 28, OBSERVANCE).validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  /**
   * Days off as published by the Ministry of Internal Affairs (mpb.rks-gov.net) per year.
   */
  static Stream<Arguments> daysOffPublishedByTheMinistry() {
    return Stream.of(
      arguments(2023, List.of(
        LocalDate.of(2023, JANUARY, 2), LocalDate.of(2023, JANUARY, 3), LocalDate.of(2023, JANUARY, 9),
        LocalDate.of(2023, FEBRUARY, 17), LocalDate.of(2023, APRIL, 10), LocalDate.of(2023, APRIL, 11),
        LocalDate.of(2023, APRIL, 17), LocalDate.of(2023, APRIL, 21), LocalDate.of(2023, MAY, 1),
        LocalDate.of(2023, MAY, 9), LocalDate.of(2023, JUNE, 28), LocalDate.of(2023, DECEMBER, 25)
      )),
      arguments(2024, List.of(
        LocalDate.of(2024, JANUARY, 1), LocalDate.of(2024, JANUARY, 2), LocalDate.of(2024, JANUARY, 8),
        LocalDate.of(2024, FEBRUARY, 19), LocalDate.of(2024, APRIL, 1), LocalDate.of(2024, APRIL, 9),
        LocalDate.of(2024, APRIL, 10), LocalDate.of(2024, MAY, 1), LocalDate.of(2024, MAY, 6),
        LocalDate.of(2024, MAY, 9), LocalDate.of(2024, JUNE, 17), LocalDate.of(2024, DECEMBER, 25)
      )),
      // both Easter Mondays fell on 21 April
      arguments(2025, List.of(
        LocalDate.of(2025, JANUARY, 1), LocalDate.of(2025, JANUARY, 2), LocalDate.of(2025, JANUARY, 7),
        LocalDate.of(2025, FEBRUARY, 17), LocalDate.of(2025, MARCH, 31), LocalDate.of(2025, APRIL, 9),
        LocalDate.of(2025, APRIL, 21), LocalDate.of(2025, MAY, 1), LocalDate.of(2025, MAY, 9),
        LocalDate.of(2025, JUNE, 6), LocalDate.of(2025, DECEMBER, 25)
      )),
      arguments(2026, List.of(
        LocalDate.of(2026, JANUARY, 1), LocalDate.of(2026, JANUARY, 2), LocalDate.of(2026, JANUARY, 7),
        LocalDate.of(2026, FEBRUARY, 17), LocalDate.of(2026, MARCH, 20), LocalDate.of(2026, APRIL, 6),
        LocalDate.of(2026, APRIL, 9), LocalDate.of(2026, APRIL, 13), LocalDate.of(2026, MAY, 1),
        LocalDate.of(2026, MAY, 11), LocalDate.of(2026, MAY, 27), LocalDate.of(2026, DECEMBER, 25)
      ))
    );
  }

  @ParameterizedTest
  @MethodSource("daysOffPublishedByTheMinistry")
  void ensuresPublicHolidaysMatchTheMinistryCalendar(final int year, final List<LocalDate> expectedDaysOff) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(KOSOVO));

    final Set<LocalDate> daysOff = holidayManager.getHolidays(Year.of(year)).stream()
      .filter(holiday -> holiday.getType() == PUBLIC_HOLIDAY)
      .map(Holiday::getDate)
      .collect(toSet());

    assertThat(daysOff).containsExactlyInAnyOrderElementsOf(expectedDaysOff);
  }
}
