package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SAINT_KITTS_AND_NEVIS;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayKNTest {

  @Test
  void ensuresHolidays() {
    assertFor(SAINT_KITTS_AND_NEVIS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
      .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CARNIVAL_DAY", JANUARY, 2).and()
      .hasFixedHoliday("BUCKLYS_UPRISING_DAY", JANUARY, 28).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", SEPTEMBER, 16).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 19).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }

  @Test
  void ensuresLabourDayIsFirstMondayInMay() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_KITTS_AND_NEVIS));

    // 2024: May 1 is Wednesday, first Monday is May 6
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, MAY, 6), "LABOUR_DAY", PUBLIC_HOLIDAY));

    // 2025: May 1 is Thursday, first Monday is May 5
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, MAY, 5), "LABOUR_DAY", PUBLIC_HOLIDAY));

    // 2022: May 1 is Sunday, first Monday is May 2
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, MAY, 2), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresEmancipationDayIsFirstMondayInAugust() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_KITTS_AND_NEVIS));

    // 2024: Aug 1 is Thursday, first Monday is Aug 5
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, AUGUST, 5), "EMANCIPATION_DAY", PUBLIC_HOLIDAY));

    // 2025: Aug 1 is Friday, first Monday is Aug 4
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, AUGUST, 4), "EMANCIPATION_DAY", PUBLIC_HOLIDAY));

    // 2022: Aug 1 is Monday, first Monday is Aug 1
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, AUGUST, 1), "EMANCIPATION_DAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresCulturamaDayIsFirstTuesdayInAugust() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_KITTS_AND_NEVIS));

    // 2024: first Tuesday is Aug 6
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, AUGUST, 6), "CULTURAMA_DAY", PUBLIC_HOLIDAY));

    // 2025: first Tuesday is Aug 5
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, AUGUST, 5), "CULTURAMA_DAY", PUBLIC_HOLIDAY));

    // 2022: first Tuesday is Aug 2
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, AUGUST, 2), "CULTURAMA_DAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresNewYearMovesFromSundayToMonday() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_KITTS_AND_NEVIS));

    // 2023: Jan 1 is Sunday, so moved to Monday Jan 2
    final Set<Holiday> holidays2023 = holidayManager.getHolidays(Year.of(2023));
    assertThat(holidays2023)
      .contains(new Holiday(LocalDate.of(2023, JANUARY, 2), "NEW_YEAR", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2023, JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresChristmasMovesWhenOnWeekend() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SAINT_KITTS_AND_NEVIS));

    // 2024: Dec 25 is Wednesday - no move
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));

    // 2022: Dec 25 is Sunday, so moved to Tuesday Dec 27
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, DECEMBER, 27), "CHRISTMAS", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2022, DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));

    // 2021: Dec 25 is Saturday, so moved to Monday Dec 27
    final Set<Holiday> holidays2021 = holidayManager.getHolidays(Year.of(2021));
    assertThat(holidays2021)
      .contains(new Holiday(LocalDate.of(2021, DECEMBER, 27), "CHRISTMAS", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2021, DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }
}
