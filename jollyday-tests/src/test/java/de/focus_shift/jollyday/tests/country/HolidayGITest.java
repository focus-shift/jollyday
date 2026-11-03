package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayCalendar.GIBRALTAR;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayGITest {

  @Test
  void ensuresHolidays() {

    assertFor(GIBRALTAR)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1973))
        .validBetween(Year.of(1974), Year.of(2500))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("COMMONWEALTH_DAY", FEBRUARY, 15)
      .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", APRIL, 28)
        .notValidBetween(Year.of(1900), Year.of(2017))
        .validBetween(Year.of(2018), Year.of(2500))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("MAY_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("VICTORY_DAY", MAY, 8)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
        .notValidBetween(Year.of(2022), Year.of(2022))
        .notValidBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedHoliday("SPRING_BANK_HOLIDAY", JUNE, 2)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("SPRING_BANK_HOLIDAY", LAST, MONDAY, MAY)
        .validBetween(Year.of(1971), Year.of(2021))
        .notValidBetween(Year.of(2022), Year.of(2022))
        .validBetween(Year.of(2023), Year.of(2500))
      .and()
      .hasFixedWeekdayHoliday("WINTER_MIDTERM_BANK_HOLIDAY", THIRD, MONDAY, FEBRUARY)
        .notValidBetween(Year.of(1900), Year.of(2021))
        .validBetween(Year.of(2022), Year.of(2023))
        .notValidBetween(Year.of(2024), Year.of(2024))
        .validBetween(Year.of(2025), Year.of(2500))
      .and()
      .hasFixedWeekdayHoliday("WINTER_MIDTERM_BANK_HOLIDAY", SECOND, MONDAY, FEBRUARY)
        .validBetween(Year.of(2024), Year.of(2024))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validBetween(Year.of(2018), Year.of(2018))
        .validBetween(Year.of(2021), Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(2019), Year.of(2020))
        .notValidBetween(Year.of(2021), Year.of(2500))
      .and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .notValidBetween(Year.of(1900), Year.of(2022))
        .validBetween(Year.of(2023), Year.of(2500))
      .and()
      .hasFixedHoliday("NATIONAL_DAY_OF_GIBRALTAR", SEPTEMBER, 10)
        .notValidBetween(Year.of(1900), Year.of(1966))
        .validBetween(Year.of(1967), Year.of(2500))
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
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .check();
  }

  // official "Bank and Public Holidays" lists of HM Government of Gibraltar
  @Test
  void ensuresOfficialDaysOff() {
    assertThat(daysOff(2018)).containsExactlyInAnyOrder(
      LocalDate.of(2018, JANUARY, 1), LocalDate.of(2018, MARCH, 12), LocalDate.of(2018, MARCH, 30), LocalDate.of(2018, APRIL, 2),
      LocalDate.of(2018, APRIL, 30), LocalDate.of(2018, MAY, 1), LocalDate.of(2018, MAY, 28), LocalDate.of(2018, JUNE, 11),
      LocalDate.of(2018, AUGUST, 27), LocalDate.of(2018, SEPTEMBER, 10), LocalDate.of(2018, DECEMBER, 25), LocalDate.of(2018, DECEMBER, 26));
    assertThat(daysOff(2019)).containsExactlyInAnyOrder(
      LocalDate.of(2019, JANUARY, 1), LocalDate.of(2019, MARCH, 11), LocalDate.of(2019, APRIL, 19), LocalDate.of(2019, APRIL, 22),
      LocalDate.of(2019, APRIL, 29), LocalDate.of(2019, MAY, 1), LocalDate.of(2019, MAY, 27), LocalDate.of(2019, JUNE, 17),
      LocalDate.of(2019, AUGUST, 26), LocalDate.of(2019, SEPTEMBER, 10), LocalDate.of(2019, DECEMBER, 25), LocalDate.of(2019, DECEMBER, 26));
    assertThat(daysOff(2020)).containsExactlyInAnyOrder(
      LocalDate.of(2020, JANUARY, 1), LocalDate.of(2020, MARCH, 9), LocalDate.of(2020, APRIL, 10), LocalDate.of(2020, APRIL, 13),
      LocalDate.of(2020, APRIL, 28), LocalDate.of(2020, MAY, 1), LocalDate.of(2020, MAY, 8), LocalDate.of(2020, MAY, 25),
      LocalDate.of(2020, JUNE, 15), LocalDate.of(2020, AUGUST, 31), LocalDate.of(2020, SEPTEMBER, 10), LocalDate.of(2020, DECEMBER, 25),
      LocalDate.of(2020, DECEMBER, 28));
    assertThat(daysOff(2021)).containsExactlyInAnyOrder(
      LocalDate.of(2021, JANUARY, 1), LocalDate.of(2021, FEBRUARY, 15), LocalDate.of(2021, APRIL, 2), LocalDate.of(2021, APRIL, 5),
      LocalDate.of(2021, APRIL, 28), LocalDate.of(2021, MAY, 3), LocalDate.of(2021, MAY, 31), LocalDate.of(2021, JUNE, 14),
      LocalDate.of(2021, AUGUST, 30), LocalDate.of(2021, SEPTEMBER, 10), LocalDate.of(2021, DECEMBER, 27), LocalDate.of(2021, DECEMBER, 28));
    assertThat(daysOff(2022)).containsExactlyInAnyOrder(
      LocalDate.of(2022, JANUARY, 3), LocalDate.of(2022, FEBRUARY, 21), LocalDate.of(2022, APRIL, 15), LocalDate.of(2022, APRIL, 18),
      LocalDate.of(2022, APRIL, 28), LocalDate.of(2022, MAY, 2), LocalDate.of(2022, JUNE, 2), LocalDate.of(2022, JUNE, 3),
      LocalDate.of(2022, JUNE, 13), LocalDate.of(2022, AUGUST, 29), LocalDate.of(2022, SEPTEMBER, 12), LocalDate.of(2022, DECEMBER, 26),
      LocalDate.of(2022, DECEMBER, 27));
    assertThat(daysOff(2023)).containsExactlyInAnyOrder(
      LocalDate.of(2023, JANUARY, 2), LocalDate.of(2023, FEBRUARY, 20), LocalDate.of(2023, APRIL, 7), LocalDate.of(2023, APRIL, 10),
      LocalDate.of(2023, APRIL, 28), LocalDate.of(2023, MAY, 1), LocalDate.of(2023, MAY, 8), LocalDate.of(2023, MAY, 29),
      LocalDate.of(2023, JUNE, 19), LocalDate.of(2023, AUGUST, 28), LocalDate.of(2023, SEPTEMBER, 11), LocalDate.of(2023, DECEMBER, 25),
      LocalDate.of(2023, DECEMBER, 26));
    assertThat(daysOff(2024)).containsExactlyInAnyOrder(
      LocalDate.of(2024, JANUARY, 1), LocalDate.of(2024, FEBRUARY, 12), LocalDate.of(2024, MARCH, 29), LocalDate.of(2024, APRIL, 1),
      LocalDate.of(2024, APRIL, 29), LocalDate.of(2024, MAY, 1), LocalDate.of(2024, MAY, 27), LocalDate.of(2024, JUNE, 17),
      LocalDate.of(2024, AUGUST, 26), LocalDate.of(2024, SEPTEMBER, 10), LocalDate.of(2024, DECEMBER, 25), LocalDate.of(2024, DECEMBER, 26));
  }

  private static List<LocalDate> daysOff(final int year) {
    return HolidayManager.getInstance(create(GIBRALTAR)).getHolidays(Year.of(year)).stream().map(Holiday::getDate).toList();
  }
}
