package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANTIGUA_AND_BARBUDA;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAGTest {

  @Test
  void ensuresHolidays() {
    assertFor(ANTIGUA_AND_BARBUDA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", DECEMBER, 9).and()
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
    // Labour Day is defined as FixedWeekday (first Monday in May),
    // which is not directly testable via CalendarCheckerApi.hasFixedHoliday.
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: May 1 is Wednesday, first Monday is May 6
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 5, 6), "LABOUR_DAY", PUBLIC_HOLIDAY));

    // 2025: May 1 is Thursday, first Monday is May 5
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, 5, 5), "LABOUR_DAY", PUBLIC_HOLIDAY));

    // 2022: May 1 is Sunday, first Monday is May 2
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, 5, 2), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresCarnivalMondayIsFirstMondayInAugust() {
    // Carnival Monday is defined as FixedWeekday (first Monday in August).
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: Aug 1 is Thursday, first Monday is Aug 5
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 8, 5), "CARNIVAL_MONDAY", PUBLIC_HOLIDAY));

    // 2025: Aug 1 is Friday, first Monday is Aug 4
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, 8, 4), "CARNIVAL_MONDAY", PUBLIC_HOLIDAY));

    // 2022: Aug 1 is Monday, first Monday is Aug 1
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, 8, 1), "CARNIVAL_MONDAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresCarnivalTuesdayIsFirstTuesdayInAugust() {
    // Carnival Tuesday is defined as FixedWeekday (first Tuesday in August).
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: first Tuesday is Aug 6
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 8, 6), "CARNIVAL_TUESDAY", PUBLIC_HOLIDAY));

    // 2025: first Tuesday is Aug 5
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, 8, 5), "CARNIVAL_TUESDAY", PUBLIC_HOLIDAY));

    // 2022: first Tuesday is Aug 2
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, 8, 2), "CARNIVAL_TUESDAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresNationalDayOfPrayerIsSecondThursdayInSeptember() {
    // National Day of Prayer is defined as FixedWeekday (second Thursday in September).
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: second Thursday is Sep 12
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 9, 12), "NATIONAL_DAY_OF_PRAYER", PUBLIC_HOLIDAY));

    // 2025: second Thursday is Sep 11
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, 9, 11), "NATIONAL_DAY_OF_PRAYER", PUBLIC_HOLIDAY));

    // 2022: second Thursday is Sep 8
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, 9, 8), "NATIONAL_DAY_OF_PRAYER", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresNewYearMovesFromSundayToMonday() {
    // 2023: Jan 1 is Sunday, so moved to Monday Jan 2
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));
    final Set<Holiday> holidays2023 = holidayManager.getHolidays(Year.of(2023));
    assertThat(holidays2023)
      .contains(new Holiday(LocalDate.of(2023, 1, 2), "NEW_YEAR", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2023, 1, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresIndependenceDayMovesWhenOnWeekend() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: Nov 1 is Friday - no move needed
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 11, 1), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));

    // 2025: Nov 1 is Saturday, so moved to Monday Nov 3
    final Set<Holiday> holidays2025 = holidayManager.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, 11, 3), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2025, 11, 1), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));
  }

  @Test
  void ensuresChristmasMovesWhenOnWeekend() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANTIGUA_AND_BARBUDA));

    // 2024: Dec 25 is Wednesday - no move
    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, 12, 25), "CHRISTMAS", PUBLIC_HOLIDAY));

    // 2022: Dec 25 is Sunday, so moved to Tuesday Dec 27
    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .contains(new Holiday(LocalDate.of(2022, 12, 27), "CHRISTMAS", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2022, 12, 25), "CHRISTMAS", PUBLIC_HOLIDAY));

    // 2021: Dec 25 is Saturday, so moved to Monday Dec 27
    final Set<Holiday> holidays2021 = holidayManager.getHolidays(Year.of(2021));
    assertThat(holidays2021)
      .contains(new Holiday(LocalDate.of(2021, 12, 27), "CHRISTMAS", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(2021, 12, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }
}
