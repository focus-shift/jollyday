package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.NAURU;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNRTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {

    assertFor(NAURU)
      // Public Service Act 2016, s. 81: falls on Saturday or Sunday -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Independence Day and Christmas Day are the two exceptions: when they fall on the
      // weekend, both the following Monday and Tuesday become holidays (see ensuresFloatingHolidays)
      .hasFixedHoliday("INDEPENDENCE_DAY", JANUARY, 31)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("DAY_FOLLOWING_INDEPENDENCE_DAY", FEBRUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Declared by President Marcus Stephen from 8 March 2010
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2010), YEAR_TO)
      .and()
      .hasFixedHoliday("CONSTITUTION_DAY", MAY, 17)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("RONPHOS_HANDOVER_DAY", JULY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      // Gazetted as a new annual public holiday from 2019
      .hasFixedHoliday("IBUMIN_EAROENI_DAY", AUGUST, 19)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2019), YEAR_TO)
      .and()
      // Renamed to Sir Hammer DeRoburt Day from 2020 (see ensuresFloatingHolidays)
      .hasFixedHoliday("NATIONAL_YOUTH_DAY", SEPTEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2009), Year.of(2019))
      .and()
      .hasFixedHoliday("SIR_HAMMER_DEROBURT_DAY", SEPTEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(Year.of(2020), YEAR_TO)
      .and()
      .hasFixedHoliday("ANGAM_DAY", OCTOBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
        .validBetween(YEAR_FROM, YEAR_TO)
      .and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_TUESDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(NAURU));

    // Independence Day (31 Jan) falls on Saturday in 2015 -> Independence Day observed Monday
    // 2 Feb, Day following Independence Day observed Tuesday 3 Feb
    assertThat(holidayDates(manager, Year.of(2015))).contains(LocalDate.of(2015, FEBRUARY, 2), LocalDate.of(2015, FEBRUARY, 3));
    assertThat(holidayDates(manager, Year.of(2015))).doesNotContain(LocalDate.of(2015, JANUARY, 31), LocalDate.of(2015, FEBRUARY, 1));

    // Independence Day falls on Sunday in 2021 -> observed Monday 1 Feb, day following Tuesday 2 Feb
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, FEBRUARY, 1), LocalDate.of(2021, FEBRUARY, 2));

    // Independence Day falls on Friday in 2020 (not a weekend, no shift for Independence Day
    // itself), but the day following (1 Feb, a Saturday) shifts under the general single-day
    // rule to Monday 3 Feb
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, JANUARY, 31), LocalDate.of(2020, FEBRUARY, 3));
    assertThat(holidayDates(manager, Year.of(2020))).doesNotContain(LocalDate.of(2020, FEBRUARY, 1), LocalDate.of(2020, FEBRUARY, 2));

    // Christmas Day falls on Saturday in 2021 -> Christmas observed Monday 27 Dec, Boxing Day
    // observed Tuesday 28 Dec
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, DECEMBER, 27), LocalDate.of(2021, DECEMBER, 28));

    // Christmas Day falls on Sunday in 2022 -> observed Monday 26 Dec, Boxing Day observed Tuesday 27 Dec
    assertThat(holidayDates(manager, Year.of(2022))).contains(LocalDate.of(2022, DECEMBER, 26), LocalDate.of(2022, DECEMBER, 27));

    // Christmas Day falls on Friday in 2020 (no shift), Boxing Day (a Saturday) shifts under the
    // general single-day rule to Monday 28 Dec
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, DECEMBER, 25), LocalDate.of(2020, DECEMBER, 28));
    assertThat(holidayDates(manager, Year.of(2020))).doesNotContain(LocalDate.of(2020, DECEMBER, 26), LocalDate.of(2020, DECEMBER, 27));

    // National Youth Day (25 Sep) through 2019, renamed Sir Hammer DeRoburt Day from 2020
    assertThat(holidayKeys(manager, Year.of(2019))).contains("NATIONAL_YOUTH_DAY");
    assertThat(holidayKeys(manager, Year.of(2019))).doesNotContain("SIR_HAMMER_DEROBURT_DAY");
    assertThat(holidayKeys(manager, Year.of(2020))).contains("SIR_HAMMER_DEROBURT_DAY");
    assertThat(holidayKeys(manager, Year.of(2020))).doesNotContain("NATIONAL_YOUTH_DAY");

    // Ibumin Earoeni Day only exists from 2019 onward
    assertThat(holidayKeys(manager, Year.of(2018))).doesNotContain("IBUMIN_EAROENI_DAY");
    assertThat(holidayKeys(manager, Year.of(2019))).contains("IBUMIN_EAROENI_DAY");

    // International Women's Day only exists from 2010 onward
    assertThat(holidayKeys(manager, Year.of(2009))).doesNotContain("INTERNATIONAL_WOMAN");
    assertThat(holidayKeys(manager, Year.of(2010))).contains("INTERNATIONAL_WOMAN");
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }

  private static Set<String> holidayKeys(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getPropertiesKey).collect(Collectors.toSet());
  }
}
