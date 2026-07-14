package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.DOMINICA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayDMTest {

  @Test
  void ensuresHolidays() {
    assertFor(DOMINICA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", NOVEMBER, 3)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_COMMUNITY_SERVICE", NOVEMBER, 4)
        .canBeMovedFrom(SUNDAY, MONDAY).canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SUNDAY, MONDAY).canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST).and()
      // Carnival Monday and Tuesday (the Monday and Tuesday before Ash Wednesday).
      .hasChristianHoliday("CARNIVAL_MONDAY", true).and()
      .hasChristianHoliday("CARNIVAL_TUESDAY", true).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }

  @Test
  void ensuresCarnivalMondayAndTuesday2026() {
    // Carnival Monday and Tuesday are the Monday and Tuesday before Ash Wednesday.
    final Set<Holiday> holidays = HolidayManager.getInstance(create(DOMINICA)).getHolidays(Year.of(2026));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CARNIVAL_MONDAY"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2026, FEBRUARY, 16));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CARNIVAL_TUESDAY"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2026, FEBRUARY, 17));
  }

  @Test
  void ensuresIndependenceAndCommunityServiceCascadeIn2024() {
    // 3 November 2024 (Independence Day) fell on a Sunday -> observed Monday 4 November,
    // which pushed National Day of Community Service to Tuesday 5 November.
    final Set<Holiday> holidays = HolidayManager.getInstance(create(DOMINICA)).getHolidays(Year.of(2024));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("INDEPENDENCE_DAY"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2024, NOVEMBER, 4));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("NATIONAL_DAY_OF_COMMUNITY_SERVICE"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2024, NOVEMBER, 5));
  }

  @Test
  void ensuresChristmasAndBoxingDayCascadeIn2022() {
    // 25 December 2022 (Christmas) fell on a Sunday -> observed Monday 26 December,
    // which pushed Boxing Day to Tuesday 27 December.
    final Set<Holiday> holidays = HolidayManager.getInstance(create(DOMINICA)).getHolidays(Year.of(2022));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("CHRISTMAS"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2022, DECEMBER, 26));
    assertThat(holidays)
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("BOXING_DAY"))
      .extracting(Holiday::getDate)
      .containsExactly(LocalDate.of(2022, DECEMBER, 27));
  }

  @Test
  void ensuresNoWeekendShiftWhenHolidayFallsOnSaturday() {
    // Boxing Day 26 December 2026 falls on a Saturday and is NOT shifted (Sunday-only rule).
    final Set<Holiday> holidays = HolidayManager.getInstance(create(DOMINICA)).getHolidays(Year.of(2026));
    assertThat(holidays)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, DECEMBER, 26));
  }
}
