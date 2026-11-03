package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PERU;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.AUGUST;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPETest {

  private static final Year YEAR_FROM = Year.of(1900);

  @Test
  void ensuresHolidays() {
    assertFor(PERU)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("ARICA", JUNE, 7)
        .notValidBetween(YEAR_FROM, Year.of(2023))
        .validFrom(Year.of(2024))
      .and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29).and()
      .hasFixedHoliday("AIR_FORCE_DAY", JULY, 23)
        .notValidBetween(YEAR_FROM, Year.of(2022))
        .validFrom(Year.of(2023))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 28).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 29).and()
      .hasFixedHoliday("JUNIN", AUGUST, 6)
        .notValidBetween(YEAR_FROM, Year.of(2021))
        .validFrom(Year.of(2022))
      .and()
      .hasFixedHoliday("SANTA_ROSA", AUGUST, 30).and()
      .hasFixedHoliday("ANGAMOS", OCTOBER, 8).and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("AYACUCHO", DECEMBER, 9)
        .notValidBetween(YEAR_FROM, Year.of(2021))
        .validFrom(Year.of(2022))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("MAUNDY_THURSDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  @ParameterizedTest
  @CsvSource({
    // dates listed on gob.pe/feriados
    "2022-08-06", "2023-07-23", "2023-08-06", "2023-12-09",
    "2024-06-07", "2024-07-23", "2024-08-06", "2024-12-09"
  })
  void ensuresHolidaysListedOnGobPe(final LocalDate date) {
    assertThat(HolidayManager.getInstance(create(PERU)).isHoliday(date)).isTrue();
  }

  @Test
  void ensuresEasterSundayIsNoHoliday() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(PERU));
    for (int year = 1900; year <= 2100; year++) {
      assertThat(holidayManager.getHolidays(Year.of(year)))
        .extracting(Holiday::getPropertiesKey)
        .doesNotContain("christian.EASTER");
    }
  }
}
