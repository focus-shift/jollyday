package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;
import java.util.stream.Collectors;

import static de.focus_shift.jollyday.core.HolidayCalendar.TURKS_AND_CAICOS_ISLANDS;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static java.time.Year.of;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayTCTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(TURKS_AND_CAICOS_ISLANDS)
      // Falls on a weekend -> observed the following Monday
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("EMANCIPATION_DAY", AUGUST, 1).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      // Boxing Day is bumped to Tuesday instead of Monday when it would otherwise collide
      // with a Christmas Day substitute
      .hasFixedHoliday("BOXING_DAY", DECEMBER, 26).validBetween(YEAR_FROM, YEAR_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, NEXT, TUESDAY)
        .canBeMovedFrom(MONDAY, NEXT, TUESDAY)
      .and()
      // 2022 Platinum Jubilee: Queen's Birthday moved to Friday 3 June, extra holiday on Monday 6 June
      .hasFixedHoliday("QUEENS_BIRTHDAY", JUNE, 3).validBetween(of(2022), of(2022)).and()
      .hasFixedHoliday("QUEENS_PLATINUM_JUBILEE", JUNE, 6).validBetween(of(2022), of(2022)).and()
      .hasChristianHoliday("GOOD_FRIDAY").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("EASTER_MONDAY").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @Test
  void ensuresFloatingHolidays() {
    final HolidayManager manager = HolidayManager.getInstance(create(TURKS_AND_CAICOS_ISLANDS));

    // Commonwealth Day: second Monday in March
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, MARCH, 10));

    // Renamed from National Heroes Day to JAGS McCartney Day in 2020; last Monday in May
    assertThat(holidayKeys(manager, Year.of(2019))).contains("NATIONAL_HEROES_DAY");
    assertThat(holidayKeys(manager, Year.of(2019))).doesNotContain("JAGS_MCCARTNEY_DAY");
    assertThat(holidayDates(manager, Year.of(2019))).contains(LocalDate.of(2019, MAY, 27));
    assertThat(holidayKeys(manager, Year.of(2020))).contains("JAGS_MCCARTNEY_DAY");
    assertThat(holidayKeys(manager, Year.of(2020))).doesNotContain("NATIONAL_HEROES_DAY");
    assertThat(holidayDates(manager, Year.of(2020))).contains(LocalDate.of(2020, MAY, 25));

    // Sovereign's official birthday: second Monday in June 2018-2021 and 2023-2024,
    // third Monday in June from 2025
    assertThat(holidayDates(manager, Year.of(2018))).contains(LocalDate.of(2018, JUNE, 11));
    assertThat(holidayDates(manager, Year.of(2021))).contains(LocalDate.of(2021, JUNE, 14));
    assertThat(holidayKeys(manager, Year.of(2023))).contains("KINGS_BIRTHDAY");
    assertThat(holidayDates(manager, Year.of(2023))).contains(LocalDate.of(2023, JUNE, 12));
    assertThat(holidayDates(manager, Year.of(2024))).contains(LocalDate.of(2024, JUNE, 10));
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, JUNE, 16));

    // Constitution Day introduced 2026, last Monday in August
    assertThat(holidayKeys(manager, Year.of(2025))).doesNotContain("CONSTITUTION_DAY");
    assertThat(holidayDates(manager, Year.of(2026))).contains(LocalDate.of(2026, AUGUST, 31));

    // National Day of Prayer and Thanksgiving: fourth Friday in November through 2025,
    // then the Sunday before Constitution Day (last Sunday in August) from 2026
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, NOVEMBER, 28));
    assertThat(holidayDates(manager, Year.of(2026))).contains(LocalDate.of(2026, AUGUST, 30));

    // National Youth Day: last Friday in September
    assertThat(holidayDates(manager, Year.of(2025))).contains(LocalDate.of(2025, SEPTEMBER, 26));

    // National Heritage Day replaced Columbus Day in 2014; second Monday in October
    assertThat(holidayKeys(manager, Year.of(2013))).contains("COLUMBUS_DAY");
    assertThat(holidayDates(manager, Year.of(2013))).contains(LocalDate.of(2013, OCTOBER, 14));
    assertThat(holidayKeys(manager, Year.of(2014))).contains("NATIONAL_HERITAGE_DAY");
    assertThat(holidayDates(manager, Year.of(2014))).contains(LocalDate.of(2014, OCTOBER, 13));
  }

  private static Set<LocalDate> holidayDates(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getDate).collect(Collectors.toSet());
  }

  private static Set<String> holidayKeys(final HolidayManager manager, final Year year) {
    return manager.getHolidays(year).stream().map(Holiday::getPropertiesKey).collect(Collectors.toSet());
  }
}
