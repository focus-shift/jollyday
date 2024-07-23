package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.MOLDOVA;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayMDTest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatOrthodoxChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 7), "CHRISTMAS_EVE", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 8), "CHRISTMAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresInternationalWomanDayIsNotConfiguredBefore1977(@ForAll @YearRange(max = 1976) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("INTERNATIONAL_WOMAN");
  }

  @Property
  void ensuresInternationalWomanDayIsConfiguredSince1977(@ForAll @YearRange(min = 1977) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MARCH, 8), "INTERNATIONAL_WOMAN", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatChildrensDayIsNotConfiguredBefore2016(@ForAll @YearRange(max = 2015) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("CHILDRENS_DAY");
  }

  @Property
  void ensuresThatChildrensDayIsConfiguredSine2016(@ForAll @YearRange(min = 2016) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 1), "CHILDRENS_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatVictoryDayIsConfiguredSince1965(@ForAll @YearRange(min = 1965) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 9), "VICTORY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatVictoryDayIsConfiguredBefore1965(@ForAll @YearRange(max = 1964) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("VICTORY");
  }

  @Property
  void ensuresThatIndependenceDayIsConfiguredSince1991(@ForAll @YearRange(min = 1991) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 27), "INDEPENDENCE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceDayIsNotConfiguredBefore1991(@ForAll @YearRange(max = 1990) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("INDEPENDENCE_DAY");
  }

  @Property
  void ensuresThatLanguageDayIsConfiguredSince1990(@ForAll @YearRange(min = 1990) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 31), "LANGUAGE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLanguageDayIsNotConfiguredBefore1990(@ForAll @YearRange(max = 1989) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("LANGUAGE_DAY");
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatBaltiDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOLDOVA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year, "ba");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 22), "REGIONAL", OFFICIAL_HOLIDAY));
  }
}
