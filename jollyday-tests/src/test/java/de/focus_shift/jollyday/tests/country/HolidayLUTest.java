package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.LUXEMBOURG;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayLUTest {

  @Property
  void ensuresThatNewYearConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEuropeDayIsConfiguredSince2019(@ForAll @YearRange(min = 2019) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 9), "EUROPE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEuropeDayIsNotConfiguredUntil2018(@ForAll @YearRange(max = 2018) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 9), "EUROPE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNationalDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 23), "NATIONAL_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAssumptionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "ASSUMPTION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAllSaintsIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 1), "ALL_SAINTS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStephensIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatAscensionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.ASCENSION_DAY");
  }

  @Property
  void ensuresThatWhitMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.WHIT_MONDAY");
  }

  @Property
  void ensuresThatCarnivalForCityOfLuxemburgIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(LUXEMBOURG));
    final Set<Holiday> holidays = holidayManager.getHolidays(year, "lu", "clu");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), FEBRUARY, 15), "CARNIVAL", PUBLIC_HOLIDAY));
  }
}
