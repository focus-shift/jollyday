package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.CAYMAN_ISLANDS;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.FOUR_YEARS;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HolidayKYTest {

  @Test
  void ensuresHolidays() {

    assertFor(CAYMAN_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("ELECTION_DAY", MAY, 20)
        .validBetween(Year.of(2009), Year.of(2009))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedHoliday("CONSTITUTION_COMMENCEMENT_2009", NOVEMBER, 6)
        .validBetween(Year.of(2009), Year.of(2009))
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedWeekdayHoliday("NATIONAL_HEROES_DAY", FOURTH, MONDAY, JANUARY).and()
      .hasFixedWeekdayHoliday("DISCOVERY_DAY", THIRD, MONDAY, MAY)
        .validTo(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("EMANCIPATION_DAY", FIRST, MONDAY, MAY)
        .validFrom(Year.of(2024))
      .and()
      // Queen's Birthday: "the Monday following the Saturday appointed in the UK as the
      // reigning sovereign's official birthday" - the actual which/weekday combo has changed
      // year to year, so every observed year is asserted as its own single-year window.
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(1952), Year.of(2009))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validBetween(Year.of(2010), Year.of(2011))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(2012), Year.of(2015))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validBetween(Year.of(2016), Year.of(2016))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(2017), Year.of(2017))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validBetween(Year.of(2018), Year.of(2019))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", THIRD, MONDAY, JUNE)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validBetween(Year.of(2021), Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", FIRST, MONDAY, JUNE)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      .hasFixedWeekdayHoliday("KINGS_DAY", THIRD, MONDAY, JUNE)
        .validFrom(Year.of(2023))
      .and()
      .hasFixedWeekdayHoliday("CONSTITUTION_DAY", FIRST, MONDAY, JULY).and()
      .hasFixedWeekdayHoliday("CAYMAN_THANKSGIVING", FIRST, SUNDAY, DECEMBER)
        .validFrom(Year.of(2023))
      .and()
      .hasChristianHoliday("ASH_WEDNESDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasFixedWeekdayBetweenFixedHoliday("REMEMBRANCE", MONDAY, MonthDay.of(NOVEMBER, 9), MonthDay.of(NOVEMBER, 15))
        .validFrom(Year.of(1919))
      .and()
      .hasFixedWeekdayHoliday("ELECTION_DAY", SECOND, WEDNESDAY, APRIL)
        .validFrom(Year.of(2021))
        .every(FOUR_YEARS, Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("ELECTION_DAY", FOURTH, WEDNESDAY, MAY)
        .validBetween(Year.of(2013), Year.of(2017))
        .every(FOUR_YEARS, Year.of(2013))
      .check();
  }

  @Test
  void testManagerKYInterval() {
    assertDoesNotThrow(() -> {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.CAYMAN_ISLANDS, null));
      final LocalDate startDateInclusive = LocalDate.of(2022, Month.OCTOBER, 1);
      final LocalDate endDateInclusive = LocalDate.of(2023, Month.JANUARY, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2022, Month.NOVEMBER, 14),
        LocalDate.of(2022, Month.DECEMBER, 26), LocalDate.of(2022, Month.DECEMBER, 27),
        LocalDate.of(2023, Month.JANUARY, 2), LocalDate.of(2023, Month.JANUARY, 23));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    });
  }
}
