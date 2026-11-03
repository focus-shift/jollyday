package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GRENADA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayGDTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(GRENADA)
      // Bank Holidays Act, Chapter 25: falls on a Sunday -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", FEBRUARY, 7)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Fixed to 1 August from 2025 onward; previously the first Monday in August (see ensuresFloatingHolidays)
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2025), YEAR_TO)
      .and()
      // Superseded by the fixed 1 August date from 2025 onward (above).
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST)
        .validTo(Year.of(2024))
        // Not a general absence claim: from 2026 onward the fixed 1 August date can coincide
        // with the first Monday in August (e.g. 2027), so only the single verified year is asserted.
        .notValidBetween(Year.of(2025), Year.of(2025))
      .and()
      // Introduced 2023, commemorating the killing of PM Maurice Bishop on 19 October 1983
      .hasFixedHoliday("NATIONAL_HEROES_DAY", OCTOBER, 19)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2023), YEAR_TO)
        .notValidBetween(YEAR_FROM, Year.of(2022))
      .and()
      .hasFixedHoliday("THANKSGIVING", OCTOBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Christmas on a Sunday: Monday is Boxing Day, so Tuesday 27 December is the Bank Holiday
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // one-off Bank Holidays by proclamation
      .hasFixedHoliday("BANK_HOLIDAY", JUNE, 24)
        .validBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2021), Year.of(2021))
        .notValidBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("CARICOM_DAY", JULY, 4)
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("CORPUS_CHRISTI").validBetween(YEAR_FROM, YEAR_TO).and()
      // Spicemas: modeled as two full-day holidays, matching how it's actually observed.
      .hasFixedWeekdayHoliday("CARNIVAL_MONDAY", SECOND, MONDAY, AUGUST).and()
      .hasRelativeToWeekdayInMonthHoliday("CARNIVAL_TUESDAY", TUESDAY, AFTER, SECOND, MONDAY, AUGUST)
      .check();
  }

  @Test
  void ensuresCarnivalTuesdayIsTheDayAfterCarnivalMonday() {
    // official 2023 list: Carnival Monday 14 August, Carnival Tuesday 15 August
    final HolidayManager holidayManager = HolidayManager.getInstance(create(GRENADA));
    assertThat(holidayManager.isHoliday(LocalDate.of(2023, AUGUST, 14))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2023, AUGUST, 15))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2023, AUGUST, 8))).isFalse();
    assertThat(holidayManager.isHoliday(LocalDate.of(2028, AUGUST, 15))).isTrue();
    assertThat(holidayManager.isHoliday(LocalDate.of(2028, AUGUST, 8))).isFalse();
  }

  @Test
  void ensuresTuesdayAfterSundayChristmasIsABankHoliday() {
    // S.R.O. 35/2011, 61/2016, 43/2022
    final HolidayManager holidayManager = HolidayManager.getInstance(create(GRENADA));
    for (final int year : new int[]{2011, 2016, 2022}) {
      assertThat(holidayManager.isHoliday(LocalDate.of(year, DECEMBER, 26))).isTrue();
      assertThat(holidayManager.isHoliday(LocalDate.of(year, DECEMBER, 27))).isTrue();
    }
  }
}
