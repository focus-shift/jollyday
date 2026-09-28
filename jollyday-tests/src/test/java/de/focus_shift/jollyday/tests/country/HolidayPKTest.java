package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayCalendar.PAKISTAN;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPKTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  private static final String FITR = "islamic.ID_AL_FITR";
  private static final String ADHA = "islamic.ID_UL_ADHA";
  private static final String ASHURA = "islamic.ASCHURA";
  private static final String MILAD = "islamic.MAWLID_AN_NABI";

  @Test
  void ensuresHolidays() {
    assertFor(PAKISTAN)
      .hasFixedHoliday("KASHMIR_DAY", FEBRUARY, 5).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("PAKISTAN_DAY", MARCH, 23).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("YEOM_E_TAKBEER", MAY, 28)
        .notValidBetween(YEAR_FROM, Year.of(2023))
        .validBetween(Year.of(2024), YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 14).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("IQBAL_DAY", NOVEMBER, 9).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("QUAID_E_AZAM_DAY", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  /**
   * Dates as declared by the Cabinet Division notifications after the moon sighting.
   * Where a notification differs between offices with a five- and a six-day working week,
   * the extra Saturday for six-day offices is included.
   */
  static Stream<Arguments> officiallyNotifiedIslamicHolidays() {
    return Stream.of(
      Arguments.of(2023, List.of(
        FITR + " 2023-04-21", FITR + " 2023-04-22", FITR + " 2023-04-23", FITR + " 2023-04-24", FITR + " 2023-04-25",
        ADHA + " 2023-06-28", ADHA + " 2023-06-29", ADHA + " 2023-06-30", ADHA + " 2023-07-01",
        ASHURA + " 2023-07-28", ASHURA + " 2023-07-29",
        MILAD + " 2023-09-29"
      )),
      Arguments.of(2024, List.of(
        FITR + " 2024-04-10", FITR + " 2024-04-11", FITR + " 2024-04-12", FITR + " 2024-04-13",
        ADHA + " 2024-06-17", ADHA + " 2024-06-18", ADHA + " 2024-06-19",
        ASHURA + " 2024-07-16", ASHURA + " 2024-07-17",
        MILAD + " 2024-09-17"
      )),
      Arguments.of(2025, List.of(
        FITR + " 2025-03-31", FITR + " 2025-04-01", FITR + " 2025-04-02",
        ADHA + " 2025-06-06", ADHA + " 2025-06-07", ADHA + " 2025-06-08", ADHA + " 2025-06-09",
        ASHURA + " 2025-07-05", ASHURA + " 2025-07-06",
        MILAD + " 2025-09-06"
      )),
      Arguments.of(2026, List.of(
        FITR + " 2026-03-20", FITR + " 2026-03-21",
        ADHA + " 2026-05-26", ADHA + " 2026-05-27", ADHA + " 2026-05-28",
        ASHURA + " 2026-06-25", ASHURA + " 2026-06-26",
        MILAD + " 2026-08-26"
      ))
    );
  }

  @ParameterizedTest
  @MethodSource("officiallyNotifiedIslamicHolidays")
  void ensuresOfficiallyNotifiedIslamicHolidays(final int year, final List<String> expected) {
    assertThat(islamicHolidays(year)).containsExactlyElementsOf(expected);
  }

  @ParameterizedTest
  @ValueSource(ints = {2022, 2027})
  void ensuresCalculatedIslamicHolidaysOutsideOfNotifiedYears(final int year) {
    assertThat(islamicHolidays(year))
      .hasSize(5)
      .filteredOn(holiday -> holiday.startsWith(ASHURA + " ")).hasSize(2);
  }

  @Test
  void ensuresYoumETakbeerCoincidesWithEidUlAdhaIn2026() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(PAKISTAN));
    assertThat(holidayManager.getHolidays(Year.of(2026)))
      .filteredOn(holiday -> holiday.getDate().equals(LocalDate.of(2026, MAY, 28)))
      .extracting(Holiday::getPropertiesKey)
      .containsExactlyInAnyOrder("YEOM_E_TAKBEER", ADHA);
  }

  private static List<String> islamicHolidays(final int year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(PAKISTAN));
    return holidayManager.getHolidays(Year.of(year)).stream()
      .filter(holiday -> holiday.getPropertiesKey().startsWith("islamic."))
      .sorted(Comparator.comparing(Holiday::getDate).thenComparing(Holiday::getPropertiesKey))
      .map(holiday -> holiday.getPropertiesKey() + " " + holiday.getDate())
      .toList();
  }
}
