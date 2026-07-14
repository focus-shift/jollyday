package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.PAPUA_NEW_GUINEA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayPGTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(PAPUA_NEW_GUINEA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("SOMARE_REMEMBRANCE_DAY", FEBRUARY, 26).validFrom(Year.of(2022)).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("REMEMBRANCE", JULY, 23).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("REPENTANCE_DAY", AUGUST, 26).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 16).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, TUESDAY).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO).canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_SATURDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE).validTo(Year.of(2022)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validFrom(Year.of(2023))
      .check();
  }

  @Test
  void ensuresChristmasOnSundayIsObservedOnFollowingTuesday() {
    // 25 December 2022 fell on a Sunday, so Christmas is observed on Tuesday 27 December
    // while Boxing Day is observed on Monday 26 December.
    final HolidayManager holidayManager = HolidayManager.getInstance(create(PAPUA_NEW_GUINEA));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(2022));

    assertThat(holidays)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2022, DECEMBER, 26))  // Boxing Day (Monday)
      .contains(LocalDate.of(2022, DECEMBER, 27)); // Christmas observed (Tuesday)
  }

  @Test
  void ensuresIndependenceDayOnSundayIsObservedOnFollowingMonday() {
    // 16 September 2018 fell on a Sunday, so Independence Day is observed on Monday 17 September
    final HolidayManager holidayManager = HolidayManager.getInstance(create(PAPUA_NEW_GUINEA));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(2018));

    assertThat(holidays)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2018, SEPTEMBER, 17));
  }
}
