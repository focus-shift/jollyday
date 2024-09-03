package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.CROATIA;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayHRTest {

  @Property
  void ensuresThatNewYearIsOnFirstJanuary(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNationalDayIsConfiguredSince2020(@ForAll @YearRange(min = 2020) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 30), "NATIONAL_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNationalDayIsNotConfiguredUntil2019(@ForAll @YearRange(max = 2019) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), MAY, 30), "NATIONAL_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAntiFascistIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 22), "ANTI_FASCIST", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStatehoodIsNotConfiguredTo2001(@ForAll @YearRange(max = 2001) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JUNE, 25), "STATEHOOD", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStatehoodIsConfiguredFrom2002Until2019(@ForAll @YearRange(min = 2002, max = 2019) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 25), "STATEHOOD", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStatehoodIsNotConfiguredSince2020(@ForAll @YearRange(min = 2020) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JUNE, 25), "STATEHOOD", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatVictoryIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 5), "VICTORY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAssumptionMaryIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "ASSUMPTION_MARY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceDayIsNotConfiguredTo2001(@ForAll @YearRange(max = 2001) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 8), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceDayIsConfiguredFrom2002Until2019(@ForAll @YearRange(min = 2002, max = 2019) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 8), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceDayIsNotConfiguredSince2020(@ForAll @YearRange(min = 2020) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 8), "INDEPENDENCE_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAllSaintsIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 1), "ALL_SAINTS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatRemembranceIsConfiguredSince2020(@ForAll @YearRange(min = 2020) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 18), "REMEMBRANCE", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatRemembranceIsNotConfiguredUntil2019(@ForAll @YearRange(max = 2019) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 18), "REMEMBRANCE", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatFirstAndSecondChristmasIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatCorpusChristiIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CROATIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.CORPUS_CHRISTI");
  }
}
