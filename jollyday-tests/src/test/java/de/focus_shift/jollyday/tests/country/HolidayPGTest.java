package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

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
      .hasFixedHoliday("SOMARE_REMEMBRANCE_DAY", FEBRUARY, 26).validFrom(Year.of(2022)).and()
      .hasFixedHoliday("KINGS_BIRTHDAY", JUNE, 17).validFrom(Year.of(2026)).canBeMovedFrom(SUNDAY, MONDAY).and()
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
      .hasFixedWeekdayHoliday("KINGS_BIRTHDAY", SECOND, MONDAY, JUNE).validBetween(Year.of(2023), Year.of(2025))
      .check();
  }

  @Test
  void ensuresKingsBirthdayDates() {
    assertThat(datesOf("KINGS_BIRTHDAY", 2023)).containsExactly(LocalDate.of(2023, JUNE, 12));
    assertThat(datesOf("KINGS_BIRTHDAY", 2026)).containsExactly(LocalDate.of(2026, JUNE, 17));
    assertThat(datesOf("KINGS_BIRTHDAY", 2027)).containsExactly(LocalDate.of(2027, JUNE, 17));
    // 17 June 2029 is a Sunday
    assertThat(datesOf("KINGS_BIRTHDAY", 2029)).containsExactly(LocalDate.of(2029, JUNE, 18));
  }

  @Test
  void ensuresSomareRemembranceDayOff2023() {
    // Sun 26 Feb 2023: day off appointed on Fri 24 Feb 2023, no Monday substitute
    assertThat(datesOf("SOMARE_REMEMBRANCE_DAY", 2023))
      .containsExactlyInAnyOrder(LocalDate.of(2023, FEBRUARY, 24), LocalDate.of(2023, FEBRUARY, 26));
    assertThat(datesOf("SOMARE_REMEMBRANCE_DAY", 2034)).containsExactly(LocalDate.of(2034, FEBRUARY, 26));
  }

  private static List<LocalDate> datesOf(final String propertiesKey, final int year) {
    return HolidayManager.getInstance(create(PAPUA_NEW_GUINEA)).getHolidays(Year.of(year)).stream()
      .filter(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .map(Holiday::getDate)
      .toList();
  }
}
