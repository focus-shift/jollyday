package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.NETHERLANDS;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNLTest {

  @Test
  void ensuresHolidays() {
    assertFor(NETHERLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notBetween(Year.of(1900), Year.of(1966))
        .between(Year.of(1967), Year.of(2500))
      .and()
      .hasFixedHoliday("KINGS_DAY", APRIL, 27)
        .notBetween(Year.of(1900), Year.of(2013))
        .between(Year.of(2014), Year.of(2500))
        .canBeShiftedFrom(SUNDAY, PREVIOUS, SATURDAY)
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", AUGUST, 31)
        .between(Year.of(1885), Year.of(1947))
        .notBetween(Year.of(1948), Year.of(2500))
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", APRIL, 30)
        .between(Year.of(1948), Year.of(1979))
        .canBeShiftedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("QUEENS_BIRTHDAY", APRIL, 30)
        .between(Year.of(1980), Year.of(2013))
        .canBeShiftedFrom(SUNDAY, PREVIOUS, SATURDAY)
      .and()
      .hasFixedHoliday("LIBERATION", MAY, 5)
        .between(Year.of(1990), Year.of(2500))
      .and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("PENTECOST").and()
      .hasChristianHoliday("PENTECOST_MONDAY")
      .check();
  }

  @Property
  void ensuresThatAllLiberationIsConfiguredEveryFiveYearsSince1945Until1989(@ForAll @YearRange(min = 1945, max = 1989) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(NETHERLANDS));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    if (year.getValue() % 5 == 0) {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 5), "LIBERATION", PUBLIC_HOLIDAY));
    }
  }
}
