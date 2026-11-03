package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.BELIZE;
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
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayBZTest {

  @Test
  void ensuresHolidays() {
    assertFor(BELIZE)
      // Only move when they fall on a Sunday (-> following Monday)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("GEORGE_PRICE_DAY", JANUARY, 15).validFrom(Year.of(2021))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("SAINT_GEORGES_CAYE_DAY", SEPTEMBER, 10)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 21)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("GARIFUNA_SETTLEMENT_DAY", NOVEMBER, 19)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Tue/Wed -> preceding Monday, Sun -> following Monday
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).validFrom(Year.of(2021))
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Always on a Monday - Tue/Wed/Thu -> preceding Monday, Fri/Sat/Sun -> following Monday
      .hasFixedHoliday("NATIONAL_HEROES_AND_BENEFACTORS_DAY", MARCH, 9)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDIGENOUS_PEOPLES_RESISTANCE_DAY", OCTOBER, 12).validFrom(Year.of(2022))
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Kept on its statutory date (never moved)
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("COMMONWEALTH_DAY", MAY, 24).validBetween(Year.of(2021), Year.of(2021)).and()
      // Christian holidays (Easter based). EASTER_SATURDAY is Holy Saturday (Easter Sunday - 1).
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }

  @Test
  void ensuresDatesFromOfficialNotices() {
    // Government of Belize Press Office "Public and Bank Holidays" notices 2021-2024
    assertThat(datesOf(2021, "GEORGE_PRICE_DAY")).containsExactly(LocalDate.of(2021, JANUARY, 15));
    assertThat(datesOf(2021, "COMMONWEALTH_DAY")).containsExactly(LocalDate.of(2021, MAY, 24));
    assertThat(datesOf(2021, "EMANCIPATION_DAY")).containsExactly(LocalDate.of(2021, AUGUST, 2));
    assertThat(datesOf(2021, "INDIGENOUS_PEOPLES_RESISTANCE_DAY")).isEmpty();
    assertThat(datesOf(2021, "BOXING_DAY")).containsExactly(LocalDate.of(2021, DECEMBER, 27));
    assertThat(datesOf(2022, "LABOUR_DAY")).containsExactly(LocalDate.of(2022, MAY, 2));
    assertThat(datesOf(2022, "COMMONWEALTH_DAY")).isEmpty();
    assertThat(datesOf(2022, "CHRISTMAS")).containsExactly(LocalDate.of(2022, DECEMBER, 25));
    assertThat(datesOf(2022, "BOXING_DAY")).containsExactly(LocalDate.of(2022, DECEMBER, 26));
    assertThat(datesOf(2023, "GEORGE_PRICE_DAY")).containsExactly(LocalDate.of(2023, JANUARY, 16));
    assertThat(datesOf(2023, "EMANCIPATION_DAY")).containsExactly(LocalDate.of(2023, JULY, 31));
    assertThat(datesOf(2024, "NATIONAL_HEROES_AND_BENEFACTORS_DAY")).containsExactly(LocalDate.of(2024, MARCH, 11));
    assertThat(datesOf(2024, "LABOUR_DAY")).containsExactly(LocalDate.of(2024, APRIL, 29));
    assertThat(datesOf(2024, "EMANCIPATION_DAY")).containsExactly(LocalDate.of(2024, AUGUST, 1));
    assertThat(datesOf(2024, "INDIGENOUS_PEOPLES_RESISTANCE_DAY")).containsExactly(LocalDate.of(2024, OCTOBER, 14));
    assertThat(datesOf(2025, "GEORGE_PRICE_DAY")).containsExactly(LocalDate.of(2025, JANUARY, 15));
    assertThat(datesOf(2026, "GEORGE_PRICE_DAY")).containsExactly(LocalDate.of(2026, JANUARY, 15));
    assertThat(datesOf(2026, "LABOUR_DAY")).containsExactly(LocalDate.of(2026, MAY, 1));
  }

  private static Set<LocalDate> datesOf(final int year, final String propertiesKey) {
    final Set<Holiday> holidays = HolidayManager.getInstance(create(BELIZE)).getHolidays(Year.of(year));
    return holidays.stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .map(Holiday::getDate)
      .collect(toSet());
  }
}
