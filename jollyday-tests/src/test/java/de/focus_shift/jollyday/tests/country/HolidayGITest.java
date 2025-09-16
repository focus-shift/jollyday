package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.GIBRALTAR;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.*;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayGITest {

  @Test
  void ensuresHolidays() {

    assertFor(GIBRALTAR)
      .hasFixedHoliday("COMMONWEALTH_DAY", FEBRUARY, 15)
      . between(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", APRIL, 28)
        .notBetween(Year.of(1900), Year.of(2020))
        .between(Year.of(2021), Year.of(2500))
      .and()
      .hasFixedHoliday("MAY_DAY", MAY, 1).and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8)
        .between(Year.of(2020), Year.of(2020))
      .and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  @Test
  void testManagerGINewYearsMovingDaysWhenOnSunday() {
    doNewYearsContainmentTest(2023, 2);
  }

  @Test
  void testManagerGINewYearsMovingDaysWhenOnSaturday() {
    doNewYearsContainmentTest(2022, 3);
  }

  @Test
  void testManagerGINationalDayMovingDaysWhenOnSunday() {
    doGibraltarNationalDayContainmentTest(2023, 11);
  }

  @Test
  void testManagerGINationalDayMovingDaysWhenOnSaturday() {
    doGibraltarNationalDayContainmentTest(2022, 12);
  }

  @Test
  void testManagerGIChristmasMovingDaysWhenChristmasOnSunday() {
    doChristmasContainmentTest(2022, 26, 27);
  }

  @Test
  void testManagerGIChristmasMovingDaysWhenChristmasOnSaturday() {
    doChristmasContainmentTest(2021, 27, 28);
  }

  @Test
  void testManagerGIChristmasMovingDaysWhenChristmasOnFriday() {
    doChristmasContainmentTest(2020, 25, 28);
  }

  private void doGibraltarNationalDayContainmentTest(int year, int dayOfNationalDay) {
    final LocalDate nationalDay = LocalDate.of(year, 9, dayOfNationalDay);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GIBRALTAR));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));
    assertThat(contains(nationalDay, holidays)).isTrue();
  }

  private void doNewYearsContainmentTest(int year, int dayOfNewYear) {
    final LocalDate newYear = LocalDate.of(year, 1, dayOfNewYear);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GIBRALTAR));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));
    assertThat(contains(newYear, holidays)).isTrue();
  }

  private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
    final LocalDate christmas = LocalDate.of(year, 12, dayOfChristmas);
    final LocalDate boxingday = LocalDate.of(year, 12, dayOfBoxingday);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GIBRALTAR));
    final Set<Holiday> holidays = holidayManager.getHolidays(Year.of(year));
    assertThat(contains(christmas, holidays)).isTrue();
    assertThat(contains(boxingday, holidays)).isTrue();
  }

  private boolean contains(LocalDate localDate, Set<Holiday> holidays) {
    for (Holiday h : holidays) {
      if (localDate.equals(h.getDate())) {
        return true;
      }
    }
    return false;
  }


}
