package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SLOWENIA;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidaySITest {

  @Property
  void ensuresThatNewYearOnFirstIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNewYearOnSecondIsConfiguredSince1955AndUntil2012(@ForAll @YearRange(min = 1955, max = 2012) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 2), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNewYearOnSecondIsNotConfiguredBefore1955(@ForAll @YearRange(max = 1954) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JANUARY, 2), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNewYearOnSecondIsNotConfiguredBetween2013And2016(@ForAll @YearRange(min = 2013, max = 2016) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JANUARY, 2), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatPreserenDayIsConfiguredSince1991(@ForAll @YearRange(min = 1991) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), FEBRUARY, 8), "PRESEREN", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatPreserenDayIsNotConfiguredUntil1990(@ForAll @YearRange(max = 1990) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "PRESEREN", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLiberationDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), APRIL, 27), "LIBERATION", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfiguredSince1949(@ForAll @YearRange(min = 1949) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", PUBLIC_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 2), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsNotConfiguredUntil1948(@ForAll @YearRange(max = 1948) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", PUBLIC_HOLIDAY))
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 2), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStateHoodIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 25), "STATEHOOD", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatSolidarityDayIsNotConfiguredUntil2022(@ForAll @YearRange(max = 2022) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), AUGUST, 14), "SOLIDARITY_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatSolidarityDayIsConfiguredIn2023(@ForAll @YearRange(min = 2023, max = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 14), "SOLIDARITY_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatSolidarityDayIsNotConfiguredSince2024(@ForAll @YearRange(min = 2024) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), AUGUST, 14), "SOLIDARITY_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAssumptionDayIsConfiguredSince1992(@ForAll @YearRange(min = 1992) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "ASSUMPTION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAssumptionDayIsNotConfiguredUntil1991(@ForAll @YearRange(max = 1991) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "ASSUMPTION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatReformationDayIsConfiguredSince1992(@ForAll @YearRange(min = 1992) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 31), "REFORMATION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatReformationDayIsNotConfiguredUntil1991(@ForAll @YearRange(max = 1991) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 31), "REFORMATION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAllSaintsIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 1), "ALL_SAINTS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfiguredUntil1952(@ForAll @YearRange(max = 1952) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfiguredSince1991(@ForAll @YearRange(min = 1991) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsNotConfiguredSince1953Until1990(@ForAll @YearRange(min = 1953, max = 1990) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatPentecostIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SLOWENIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.PENTECOST");
  }
}
