package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayCalendar.MONTSERRAT;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayMSTest {

  @Test
  void ensuresHolidays() {
    assertFor(MONTSERRAT)
      // Fixed date holidays with weekend substitution (Public Holidays Act, Cap. 6.09, Schedule paragraphs 2-5)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedHoliday("ST_PATRICK", MARCH, 17)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY).and()
      .hasFixedHoliday("FESTIVAL_DAY", DECEMBER, 31)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // Christian holidays (Easter-based)
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY).and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE).validTo(Year.of(2022)).and()
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedWeekdayHoliday("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", SECOND, WEDNESDAY, JULY).validFrom(Year.of(2023)).and()
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, AUGUST)
      .check();
  }

  @Test
  void ensuresKingsBirthdayIsProclaimedEachYear() {
    assertThat(datesOf("KINGS_BIRTHDAY", 2024)).containsExactly(LocalDate.of(2024, JUNE, 17));
    assertThat(datesOf("KINGS_BIRTHDAY", 2025)).containsExactly(LocalDate.of(2025, JUNE, 16));
    // Sat 13 June 2026 is observed on the following Monday (Schedule paragraph 2)
    assertThat(datesOf("KINGS_BIRTHDAY", 2026)).containsExactly(LocalDate.of(2026, JUNE, 15));
    // not yet proclaimed
    assertThat(datesOf("KINGS_BIRTHDAY", 2027)).isEmpty();
  }

  @Test
  void ensuresNationalDayOfPrayerAndThanksgiving() {
    assertThat(datesOf("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", 2020)).isEmpty();
    assertThat(datesOf("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", 2021)).containsExactly(LocalDate.of(2021, JULY, 21));
    assertThat(datesOf("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", 2022)).containsExactly(LocalDate.of(2022, JULY, 20));
    assertThat(datesOf("NATIONAL_DAY_OF_PRAYER_AND_THANKSGIVING", 2023)).containsExactly(LocalDate.of(2023, JULY, 12));
  }

  private static List<LocalDate> datesOf(final String propertiesKey, final int year) {
    return HolidayManager.getInstance(create(MONTSERRAT)).getHolidays(Year.of(year)).stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .map(Holiday::getDate)
      .toList();
  }
}
