package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.BERMUDA;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HolidayBMTest {

  @Test
  void ensuresHolidays() {

    assertFor(BERMUDA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("BERMUDA_DAY", MAY, 24)
        .validBetween(Year.of(1979), Year.of(2017))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BERMUDA_DAY", MAY, 24)
        .validBetween(Year.of(2019), Year.of(2019))
      .and()
      .hasFixedHoliday("NATIONAL_HEROES_DAY", OCTOBER, 12)
        .validBetween(Year.of(2008), Year.of(2008))
      .and()
      .hasFixedHoliday("170_ANNIVERSARY_ARRIVAL_BERMUDA", NOVEMBER, 4)
        .validBetween(Year.of(2019), Year.of(2019))
      .and()
      .hasFixedHoliday("REMEMBRANCE", NOVEMBER, 11)
        .validFrom(Year.of(1919))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validTo(Year.of(2015))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2016), Year.of(2016))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(2017))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validTo(Year.of(2015))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(Year.of(2016), Year.of(2016))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validFrom(Year.of(2017))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedWeekdayHoliday("BERMUDA_DAY", LAST, FRIDAY, MAY)
        .validBetween(Year.of(2018), Year.of(2018))
      .and()
      .hasFixedWeekdayHoliday("BERMUDA_DAY", LAST, FRIDAY, MAY)
        .validBetween(Year.of(2020), Year.of(2020))
      .and()
      .hasFixedWeekdayHoliday("BERMUDA_DAY", FOURTH, FRIDAY, MAY)
        .validFrom(Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("NATIONAL_HEROES_DAY", THIRD, MONDAY, JUNE)
        .validFrom(Year.of(2009))
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, SEPTEMBER)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("EMANCIPATION_DAY", THURSDAY, MonthDay.of(JULY, 28), MonthDay.of(AUGUST, 3))
        .validFrom(Year.of(1947))
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("SOMERS_DAY", FRIDAY, MonthDay.of(JULY, 29), MonthDay.of(AUGUST, 4))
        .validBetween(Year.of(1947), Year.of(2019))
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("MARY_PRINCE_DAY", FRIDAY, MonthDay.of(JULY, 29), MonthDay.of(AUGUST, 4))
        .validFrom(Year.of(2020))
      .check();
  }

  @Test
  void testManagerBMInterval() {
    assertDoesNotThrow(() -> {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BERMUDA, null));
      final LocalDate startDateInclusive = LocalDate.of(2022, Month.OCTOBER, 1);
      final LocalDate endDateInclusive = LocalDate.of(2023, Month.JANUARY, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2022, Month.NOVEMBER, 11),
        LocalDate.of(2022, Month.DECEMBER, 26), LocalDate.of(2022, Month.DECEMBER, 27),
        LocalDate.of(2023, Month.JANUARY, 2));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    });
  }
}
