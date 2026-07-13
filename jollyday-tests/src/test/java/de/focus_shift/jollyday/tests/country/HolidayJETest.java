package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.JERSEY;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayJETest {

  @Test
  void ensuresHolidays() {

    assertFor(JERSEY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 9)
        .validTo(Year.of(2020))
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 9)
        .validFrom(Year.of(2022))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validTo(Year.of(2010))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2011), Year.of(2011))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(2012))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validTo(Year.of(2010))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(Year.of(2011), Year.of(2011))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validFrom(Year.of(2012))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("ROYAL_WEDDING", APRIL, 29)
        .validBetween(Year.of(2011), Year.of(2011))
      .and()
      .hasFixedHoliday("75_ANNIVERSARY_VE_DAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 4)
        .validBetween(Year.of(2012), Year.of(2012))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_DIAMOND_JUBILEE", JUNE, 5)
        .validBetween(Year.of(2012), Year.of(2012))
      .and()
      .hasFixedHoliday("FUNERAL_QUEEN_ELIZABETH_II", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("CORN_RIOTS_ANNIVERSARY", SEPTEMBER, 27)
        .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2011))
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validBetween(Year.of(2013), Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .validTo(Year.of(2019))
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
        .validFrom(Year.of(2021))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }

  @Test
  void ensuresThatKingsCoronationForKingCharlesIIIIn2023() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(JERSEY));

    final Set<Holiday> holidays2022 = holidayManager.getHolidays(Year.of(2022));
    assertThat(holidays2022)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("KINGS_CORONATION");

    final Set<Holiday> holidays2023 = holidayManager.getHolidays(Year.of(2023));
    assertThat(holidays2023)
      .contains(new Holiday(LocalDate.of(2023, MAY, 8), "KINGS_CORONATION", PUBLIC_HOLIDAY));

    final Set<Holiday> holidays2024 = holidayManager.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("KINGS_CORONATION");
  }

  @Test
  void testManagerJEChristmasMovingDaysWhenChristmasOnSunday() {
    doChristmasContainmentTest(2011, 26, 27);
  }

  @Test
  void testManagerJEChristmasMovingDaysWhenChristmasOnSaturday() {
    doChristmasContainmentTest(2010, 27, 28);
  }

  @Test
  void testManagerJEChristmasMovingDaysWhenChristmasOnFriday() {
    doChristmasContainmentTest(2009, 25, 28);
  }

  private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
    final LocalDate christmas = LocalDate.of(year, Month.DECEMBER, dayOfChristmas);
    final LocalDate boxingday = LocalDate.of(year, Month.DECEMBER, dayOfBoxingday);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(JERSEY));
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
