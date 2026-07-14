package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SOLOMON_ISLANDS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static org.assertj.core.api.Assertions.assertThat;

class HolidaySBTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(SOLOMON_ISLANDS)
      // National public holidays
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 7).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_THANKSGIVING", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_SATURDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("WHIT_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasRelativeToWeekdayInMonthHoliday("QUEENS_BIRTHDAY", FRIDAY, BEFORE, SECOND, SATURDAY, JUNE).validTo(Year.of(2022)).and()
      .hasRelativeToWeekdayInMonthHoliday("KINGS_BIRTHDAY", FRIDAY, BEFORE, THIRD, SATURDAY, JUNE).validFrom(Year.of(2023)).and()
      // Provincial Province Days (Saturday -> preceding Friday, Sunday -> following Monday)
      .hasFixedHoliday("PROVINCE_DAY", JUNE, 29).inSubdivision("ce").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", FEBRUARY, 25).inSubdivision("ch").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", AUGUST, 1).inSubdivision("gu").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", JUNE, 2).inSubdivision("is").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", AUGUST, 3).inSubdivision("mk").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", AUGUST, 15).inSubdivision("ml").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", JULY, 20).inSubdivision("rb").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", JUNE, 8).inSubdivision("te").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("PROVINCE_DAY", DECEMBER, 7).inSubdivision("we").canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY).canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @Test
  void ensuresIndependenceDayOnSaturdayIsObservedOnPrecedingFriday() {
    // 7 July 2029 falls on a Saturday, so it is observed on Friday 6 July
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SOLOMON_ISLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(2029));

    assertThat(holidays)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2029, JULY, 6));
  }

  /**
   * Verifies the calendar against the primary source: the Ministry of Home Affairs
   * gazette (Public Notice No. 1/2019) listing the observed 2020 public holidays.
   */
  @Test
  void ensuresObservedDatesMatch2020Gazette() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SOLOMON_ISLANDS));

    final Set<Holiday> national = holidayManager.getHolidays(Year.of(2020));
    assertThat(national).extracting(Holiday::getDate).contains(
      LocalDate.of(2020, JANUARY, 1),    // New Year's Day (Wed)
      LocalDate.of(2020, 4, 10),         // Good Friday
      LocalDate.of(2020, 4, 11),         // Holy Saturday
      LocalDate.of(2020, 4, 13),         // Easter Monday
      LocalDate.of(2020, JUNE, 1),       // Whit Monday
      LocalDate.of(2020, JUNE, 12),      // Queen's Birthday (Sat 13 Jun -> observed Fri 12 Jun)
      LocalDate.of(2020, JULY, 7),       // Independence Day (Tue)
      LocalDate.of(2020, DECEMBER, 25),  // Christmas Day (Fri)
      LocalDate.of(2020, DECEMBER, 26)   // National Day of Thanksgiving (Sat, not shifted)
    );

    // Guadalcanal Province Day: Sat 1 Aug 2020 -> observed Fri 31 Jul 2020
    assertThat(holidayManager.getHolidays(Year.of(2020), "gu"))
      .extracting(Holiday::getDate).contains(LocalDate.of(2020, JULY, 31));
    // Malaita Province Day: Sat 15 Aug 2020 -> observed Fri 14 Aug 2020
    assertThat(holidayManager.getHolidays(Year.of(2020), "ml"))
      .extracting(Holiday::getDate).contains(LocalDate.of(2020, AUGUST, 14));
  }
}
