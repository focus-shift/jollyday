package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.TURKEY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayTRTest {

  // Java's HijrahChronology (Umm al-Qura) only covers roughly 1882-2174, so Islamic holidays
  // are asserted within this window.
  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(TURKEY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_CHILDRENS_DAY", APRIL, 23)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("SPRING_DAY", MAY, 1)
        .validBetween(Year.of(1936), Year.of(1980))
        .notValidBetween(YEAR_FROM, Year.of(1935))
        .notValidBetween(Year.of(1981), YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .validBetween(Year.of(2009), YEAR_TO)
        .notValidBetween(YEAR_FROM, Year.of(2008))
      .and()
      .hasFixedHoliday("TURKEY_COMMEMORATION_OF_ATATURK", MAY, 19)
        .validBetween(Year.of(2004), YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_DEMOCRATIC_UNITY_DAY", JULY, 15)
        .validBetween(Year.of(2017), YEAR_TO)
        .notValidBetween(YEAR_FROM, Year.of(2016))
      .and()
      .hasFixedHoliday("TURKEY_VICTORY_DAY", AUGUST, 30)
        .validBetween(Year.of(2003), YEAR_TO)
      .and()
      .hasFixedHoliday("TURKEY_REPUBLIC_DAY", OCTOBER, 29)
        .validBetween(Year.of(2003), YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_2")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_AL_FITR_3")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_2")
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasIslamicHoliday("ID_UL_ADHA_3")
        .validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  /**
   * Bayram days as published by Diyanet İşleri Başkanlığı
   * (https://vakithesaplama.diyanet.gov.tr/dinigunler.php?yil=YYYY, 2027 via icerik.php?icerik=154).
   * For 2015-2017 only the first days are taken from https://vakithesaplama.diyanet.gov.tr/dini_gunler.php;
   * in 2015 (Kurban) and 2016 (both) Diyanet differs from Umm al-Qura by one day.
   */
  @ParameterizedTest
  @CsvSource({
    // year, Ramazan day 1, day 2, day 3, Kurban day 1, day 2, day 3
    "2015, 2015-07-17, 2015-07-18, 2015-07-19, 2015-09-24, 2015-09-25, 2015-09-26",
    "2016, 2016-07-05, 2016-07-06, 2016-07-07, 2016-09-12, 2016-09-13, 2016-09-14",
    "2017, 2017-06-25, 2017-06-26, 2017-06-27, 2017-09-01, 2017-09-02, 2017-09-03",
    "2022, 2022-05-02, 2022-05-03, 2022-05-04, 2022-07-09, 2022-07-10, 2022-07-11",
    "2023, 2023-04-21, 2023-04-22, 2023-04-23, 2023-06-28, 2023-06-29, 2023-06-30",
    "2024, 2024-04-10, 2024-04-11, 2024-04-12, 2024-06-16, 2024-06-17, 2024-06-18",
    "2025, 2025-03-30, 2025-03-31, 2025-04-01, 2025-06-06, 2025-06-07, 2025-06-08",
    "2026, 2026-03-20, 2026-03-21, 2026-03-22, 2026-05-27, 2026-05-28, 2026-05-29",
    "2027, 2027-03-09, 2027-03-10, 2027-03-11, 2027-05-16, 2027-05-17, 2027-05-18",
  })
  void ensuresBayramDaysMatchDiyanet(
    final int year,
    final LocalDate fitr1, final LocalDate fitr2, final LocalDate fitr3,
    final LocalDate adha1, final LocalDate adha2, final LocalDate adha3
  ) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(TURKEY));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));

    assertThat(datesOf(holidays, "islamic.ID_AL_FITR")).containsExactly(fitr1);
    assertThat(datesOf(holidays, "islamic.ID_AL_FITR_2")).containsExactly(fitr2);
    assertThat(datesOf(holidays, "islamic.ID_AL_FITR_3")).containsExactly(fitr3);
    assertThat(datesOf(holidays, "islamic.ID_UL_ADHA")).containsExactly(adha1);
    assertThat(datesOf(holidays, "islamic.ID_UL_ADHA_2")).containsExactly(adha2);
    assertThat(datesOf(holidays, "islamic.ID_UL_ADHA_3")).containsExactly(adha3);
  }

  private static LocalDate[] datesOf(final Set<Holiday> holidays, final String propertiesKey) {
    return holidays.stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .map(Holiday::getDate)
      .toArray(LocalDate[]::new);
  }
}
