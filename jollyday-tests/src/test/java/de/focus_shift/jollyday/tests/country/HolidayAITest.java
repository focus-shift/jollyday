package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANGUILLA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Year.of;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAITest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(ANGUILLA)
      // Public Holidays Regulations, R.R.A. P130-1: falls on Sat/Sun -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("JAMES_RONALD_WEBSTER_DAY", MARCH, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2010), YEAR_TO)
      .and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("ANGUILLA_DAY", MAY, 30)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // 2022 Platinum Jubilee one-off; 2026 breaks the "third Monday" pattern (see ensuresFloatingHolidays)
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 3).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 22).validBetween(of(2026), of(2026)).and()
      // The only Anguilla holiday that moves BACKWARD to the preceding Friday when it falls on a weekend
      .hasFixedHoliday("NATIONAL_HEROES_AND_HEROINES_DAY", DECEMBER, 19)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, PREVIOUS, FRIDAY)
        .validBetween(Year.of(2011), YEAR_TO)
      .and()
      .hasFixedHoliday("SEPARATION", DECEMBER, 19)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, PREVIOUS, FRIDAY)
        .validBetween(YEAR_FROM, Year.of(2010))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(ANGUILLA));

    // Queen's Birthday: second Monday in June through 2021
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, JUNE, 8));
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, JUNE, 14));

    // 2022 Platinum Jubilee: fixed Friday 3 June instead of the usual formula
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, JUNE, 3));

    // The official 2023 circular (Ministry of Home Affairs, 2 Nov 2022) lists no Queen's/King's
    // Birthday holiday at all for 2023
    assertThat(holidayKeys(manager, Year.of(2023))).doesNotContain("QUEENS_BIRTHDAY", "KINGS_BIRTHDAY");

    // King's Birthday confirmed third Monday in June for 2024 and 2025 via archived gov.ai pages
    assertThat(holidayDates(manager, Year.of(2024))).contains(LocalDate.of(2024, JUNE, 17));
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, JUNE, 16));

    // 2026 breaks the third-Monday pattern (confirmed fourth Monday, 22 June); no formula could
    // be confirmed for 2027 onward so it is deliberately left unmodeled
    assertThat(holidayDates(manager, Year.of(2026))).contains(LocalDate.of(2026, JUNE, 22));
    assertThat(holidayKeys(manager, Year.of(2027))).doesNotContain("KINGS_BIRTHDAY");

    // August Monday/Thursday and Constitution Day: confirmed via the official 2023 circular
    // (Mon 7, Thu 10, Fri 11 Aug) and the 2026 official page (Mon 3, Thu 6, Fri 7 Aug)
    assertThat(holidayDates(manager, Year.of(2023)))
      .contains(LocalDate.of(2023, AUGUST, 7), LocalDate.of(2023, AUGUST, 10), LocalDate.of(2023, AUGUST, 11));
    assertThat(holidayDates(manager, Year.of(2026)))
      .contains(LocalDate.of(2026, AUGUST, 3), LocalDate.of(2026, AUGUST, 6), LocalDate.of(2026, AUGUST, 7));

    // Renamed from Separation Day to National Heroes and Heroines Day from 2011
    assertThat(holidayKeys(manager, Year.of(2010))).contains("SEPARATION");
    assertThat(holidayKeys(manager, Year.of(2010))).doesNotContain("NATIONAL_HEROES_AND_HEROINES_DAY");
    assertThat(holidayKeys(manager, Year.of(2011))).contains("NATIONAL_HEROES_AND_HEROINES_DAY");
    assertThat(holidayKeys(manager, Year.of(2011))).doesNotContain("SEPARATION");

    // National Heroes and Heroines Day / Separation Day moves to the preceding Friday on a weekend
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, DECEMBER, 18));
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, DECEMBER, 17));

    // Christmas/Boxing Day: independent Saturday->Monday / Sunday->Tuesday shifts never collide
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, DECEMBER, 25), LocalDate.of(2020, DECEMBER, 28));
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, DECEMBER, 27), LocalDate.of(2021, DECEMBER, 28));
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, DECEMBER, 26), LocalDate.of(2022, DECEMBER, 27));
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }

  private static Set<String> holidayKeys(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getPropertiesKey).collect(Collectors.toSet());
  }
}
