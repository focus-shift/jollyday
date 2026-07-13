package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ISLE_OF_MAN;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayIMTest {

  @Test
  void ensuresThatKingsCoronationForKingCharlesIIIIn2023() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ISLE_OF_MAN));

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
  void ensuresHolidays() {

    assertFor(ISLE_OF_MAN)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("TYNWALD", JULY, 5)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      // The 2020 Early May Bank Holiday was moved from the 4th to the 8th of May to mark VE Day's 75th anniversary
      .hasFixedHoliday("EARLY_MAY_BANK_HOLIDAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // The 2022 Spring Bank Holiday was moved from the 30th of May to the 2nd of June
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      // The 2015 and 2020 T.T. Bank Holidays were moved from the 5th of June to the 12th of June
      .hasFixedHoliday("TT_BANK_HOLIDAY", JUNE, 12)
        .validBetween(Year.of(2015), Year.of(2015))
      .and()
      .hasFixedHoliday("TT_BANK_HOLIDAY", JUNE, 12)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      // The 2021 T.T. Bank Holiday was moved from the 11th of June to the 27th of August
      .hasFixedHoliday("TT_BANK_HOLIDAY", AUGUST, 27)
        .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedHoliday("STATE_FUNERAL_QUEEN_ELIZABETH_II", SEPTEMBER, 19)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validBetween(Year.of(1900), Year.of(2019))
      .and()
      // 2020 it was moved to the second Friday of May (see the Fixed holiday above); resumes from 2021
      .hasFixedWeekdayHoliday("EARLY_MAY_BANK_HOLIDAY", FIRST, MONDAY, MAY)
        .validFrom(Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("LATE_MAY_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validTo(Year.of(2021))
      .and()
      // 2022 it was moved to the first Thursday of June (see the Fixed holiday above); resumes from 2023
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("SUMMER_BANK_HOLIDAY", LAST, MONDAY, AUGUST)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      // the Friday of race week (first week of June), 11 days after the start of Isle of Man TT on Spring Bank Holiday
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validBetween(Year.of(1907), Year.of(2014))
      .and()
      // 2015 it was moved to the second Friday in June as TT Senior Race was moved one week later
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validBetween(Year.of(2016), Year.of(2019))
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("TT_BANK_HOLIDAY", FRIDAY, MonthDay.of(JUNE, 5), MonthDay.of(JUNE, 11))
        .validFrom(Year.of(2022))
      .check();
  }

  @Test
  void testManagerIMChristmasMovingDaysWhenChristmasOnSunday() {
    doChristmasContainmentTest(2011, 26, 27);
  }

  @Test
  void testManagerIMChristmasMovingDaysWhenChristmasOnSaturday() {
    doChristmasContainmentTest(2010, 27, 28);
  }

  @Test
  void testManagerIMChristmasMovingDaysWhenChristmasOnFriday() {
    doChristmasContainmentTest(2009, 25, 28);
  }

  private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
    final LocalDate christmas = LocalDate.of(year, Month.DECEMBER, dayOfChristmas);
    final LocalDate boxingday = LocalDate.of(year, Month.DECEMBER, dayOfBoxingday);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(ISLE_OF_MAN));
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
