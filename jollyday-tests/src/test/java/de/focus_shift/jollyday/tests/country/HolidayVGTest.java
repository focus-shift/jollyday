package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.BRITISH_VIRGIN_ISLANDS;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.APRIL;
import static java.time.Month.MAY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.DECEMBER;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HolidayVGTest {

  @Test
  void ensuresHolidays() {

    assertFor(BRITISH_VIRGIN_ISLANDS)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // The Anniversary of the Birth of Hamilton Lavity Stoutt is always on the first Monday in the month of March
      // even though he was born on the 7th March 1929
      .hasFixedHoliday("STOUTTS_BIRTHDAY", MARCH, 7)
        .validFrom(Year.of(1995))
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(FRIDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SUNDAY, PREVIOUS, MONDAY)
      .and()
      // 2016 it was moved due to the Queen's 90th birthday
      .hasFixedHoliday("SOVEREIGNS_BIRTHDAY", APRIL, 21)
        .validBetween(Year.of(2016), Year.of(2016))
      .and()
      .hasFixedHoliday("KINGS_CORONATION", MAY, 8)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 3)
        .validBetween(Year.of(2022), Year.of(2022))
      .and()
      // 2017 it was moved one week later
      .hasFixedHoliday("SOVEREIGNS_BIRTHDAY", JUNE, 17)
        .validBetween(Year.of(2017), Year.of(2017))
      .and()
      // 2019 it was moved one week earlier
      .hasFixedHoliday("SOVEREIGNS_BIRTHDAY", JUNE, 7)
        .validBetween(Year.of(2019), Year.of(2019))
      .and()
      // 2023 it was moved one week later
      .hasFixedHoliday("SOVEREIGNS_BIRTHDAY", JUNE, 16)
        .validBetween(Year.of(2023), Year.of(2023))
      .and()
      // below information for colony day is just assumed to be the same as territory day, so may be very inaccurate
      .hasFixedHoliday("COLONY_DAY", JULY, 1)
        .validBetween(Year.of(1956), Year.of(1977))
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
      .and()
      .hasFixedHoliday("TERRITORY_DAY", JULY, 1)
        .validBetween(Year.of(1978), Year.of(2020))
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
      .and()
      .hasFixedHoliday("VIRGIN_ISLANDS_DAY", JULY, 1)
        .validFrom(Year.of(2021))
        .canBeMovedFrom(TUESDAY, MONDAY)
        .canBeMovedFrom(WEDNESDAY, MONDAY)
        .canBeMovedFrom(THURSDAY, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // assumed that the holidays before where following this rule too due to missing data for Wednesdays
      .hasFixedHoliday("ST_URSULA", OCTOBER, 21)
        .validBetween(Year.of(1900), Year.of(2015))
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
      .and()
      .hasFixedHoliday("ST_URSULA", OCTOBER, 21)
        .validBetween(Year.of(2016), Year.of(2020))
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
      .and()
      // assume that 2016 was an outlier
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(1900), Year.of(2015))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validBetween(Year.of(2016), Year.of(2016))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .validFrom(Year.of(2017))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      // assume that 2016 was an outlier
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(Year.of(1900), Year.of(2015))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validBetween(Year.of(2016), Year.of(2016))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .validFrom(Year.of(2017))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
      .and()
      .hasFixedWeekdayHoliday("FESTIVAL_MONDAY", FIRST, MONDAY, AUGUST)
        .validBetween(Year.of(1900), Year.of(2020))
      .and()
      .hasFixedWeekdayHoliday("EMANCIPATION_MONDAY", FIRST, MONDAY, AUGUST)
        .validFrom(Year.of(2021))
      .and()
      // the start year may be wrong, it is currently set to the earliest possible year
      .hasFixedWeekdayHoliday("COMMONWEALTH_DAY", SECOND, MONDAY, MARCH)
        .validBetween(Year.of(1977), Year.of(2020))
      .and()
      .hasFixedWeekdayHoliday("SOVEREIGNS_BIRTHDAY", SECOND, SATURDAY, JUNE)
        .validBetween(Year.of(1900), Year.of(2015))
      .and()
      // 2016 it was moved due to the Queen's 90th birthday - see above; 2017 moved one week later - see above
      .hasFixedWeekdayHoliday("SOVEREIGNS_BIRTHDAY", SECOND, SATURDAY, JUNE)
        .validBetween(Year.of(2018), Year.of(2018))
      .and()
      // 2019 it was moved one week earlier - see above
      .hasFixedWeekdayHoliday("SOVEREIGNS_BIRTHDAY", SECOND, FRIDAY, JUNE)
        .validBetween(Year.of(2020), Year.of(2022))
      .and()
      // 2023 it was moved one week later - see above
      .hasFixedWeekdayHoliday("SOVEREIGNS_BIRTHDAY", SECOND, FRIDAY, JUNE)
        .validFrom(Year.of(2024))
      .and()
      .hasFixedWeekdayHoliday("HEROES_AND_FOREPARENTS_DAY", THIRD, MONDAY, OCTOBER)
        .validFrom(Year.of(2021))
      .and()
      .hasFixedWeekdayHoliday("1949_GREAT_MARCH_AND_RESTORATION", FOURTH, MONDAY, NOVEMBER)
        .validFrom(Year.of(2021))
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("WHIT_MONDAY")
      .check();
  }

  /**
   * CalendarCheckerApi has no assertion method yet for the XSD's {@code RelativeToWeekdayInMonth} type
   * (a weekday relative to a *computed* FixedWeekdayInMonth anchor, as opposed to a fixed date). These four
   * holidays are the only usage of that type in this calendar, so they are verified directly against
   * HolidayManager instead.
   */
  @Test
  void ensuresHolidaysRelativeToWeekdayInMonth() {
    final HolidayManager manager = HolidayManager.getInstance(ManagerParameters.create(BRITISH_VIRGIN_ISLANDS));

    for (int year : new int[]{2018, 2019, 2020}) {
      final LocalDate firstMondayInAugust = LocalDate.of(year, AUGUST, 1).with(dayOfWeekInMonth(1, MONDAY));
      final Set<Holiday> holidays = manager.getHolidays(Year.of(year));
      assertThat(holidays).contains(new Holiday(firstMondayInAugust.plusDays(1), "FESTIVAL_TUESDAY", HolidayType.PUBLIC_HOLIDAY));
      assertThat(holidays).contains(new Holiday(firstMondayInAugust.plusDays(2), "FESTIVAL_WEDNESDAY", HolidayType.PUBLIC_HOLIDAY));
    }

    for (int year : new int[]{2021, 2022, 2023}) {
      final LocalDate firstMondayInAugust = LocalDate.of(year, AUGUST, 1).with(dayOfWeekInMonth(1, MONDAY));
      final Set<Holiday> holidays = manager.getHolidays(Year.of(year));
      assertThat(holidays).contains(new Holiday(firstMondayInAugust.plusDays(1), "EMANCIPATION_TUESDAY", HolidayType.PUBLIC_HOLIDAY));
      assertThat(holidays).contains(new Holiday(firstMondayInAugust.plusDays(2), "EMANCIPATION_WEDNESDAY", HolidayType.PUBLIC_HOLIDAY));
    }
  }

  @Test
  void testManagerVGInterval() {
    assertDoesNotThrow(() -> {
      final HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BRITISH_VIRGIN_ISLANDS, null));
      final LocalDate startDateInclusive = LocalDate.of(2015, Month.OCTOBER, 1);
      final LocalDate endDateInclusive = LocalDate.of(2016, Month.JANUARY, 31);
      final Set<Holiday> holidays = instance.getHolidays(startDateInclusive, endDateInclusive);
      final List<LocalDate> expected = List.of(LocalDate.of(2015, Month.DECEMBER, 25),
        LocalDate.of(2015, Month.DECEMBER, 28), LocalDate.of(2015, Month.OCTOBER, 19),
        LocalDate.of(2016, Month.JANUARY, 1));
      assertThat(holidays).hasSameSizeAs(expected);
      for (LocalDate d : expected) {
        assertThat(CalendarUtil.contains(holidays, d)).isTrue();
      }
    });
  }
}
