package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ROMANIA;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayROTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ro";

  @ParameterizedTest
  @ValueSource(strings = {"2016", "2023"})
  void testManagerROStructure(final Year year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Property
  void ensuresThatNewYearIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY),
        new Holiday(LocalDate.of(year.getValue(), JANUARY, 2), "NEW_YEAR", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatEpiphanyIsConfiguredSince2024(@ForAll @YearRange(min = 2024) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), JANUARY, 6), "EPIPHANY", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatEpiphanyIsNotConfiguredUntil2023(@ForAll @YearRange(max = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("EPIPHANY");
  }

  @Property
  void ensuresThatStJohnIsConfiguredSince2024(@ForAll @YearRange(min = 2024) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), JANUARY, 7), "ST_JOHN", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatStJohnIsNotConfiguredUntil2023(@ForAll @YearRange(max = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("ST_JOHN");
  }

  @Property
  void ensuresThatUnificationDayIsNotConfiguredUntil2016(@ForAll @YearRange(max = 2016) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("UNIFICATION");
  }

  @Property
  void ensuresThatUnificationDayIsConfiguredSince2017(@ForAll @YearRange(min = 2017) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 24), "UNIFICATION", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatChildrensDayIsNotConfiguredBefore2017(@ForAll @YearRange(max = 2016) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("CHILDRENS_DAY");
  }

  @Property
  void ensuresThatChildrensDayIsConfiguredSince2017(@ForAll @YearRange(min = 2017) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), JUNE, 1), "CHILDRENS_DAY", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatNavyDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "NAVY_DAY", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatStAndrewDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 30), "ST_ANDREW", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatNationalDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), DECEMBER, 1), "NATIONAL_DAY", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(
        new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY),
        new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "CHRISTMAS", OFFICIAL_HOLIDAY)
      );
  }

  @Property
  void ensuresThatGoodFridayIsNotConfiguredBefore2018(@ForAll @YearRange(max = 2017) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatGoodFridayIsConfiguredSince2018(@ForAll @YearRange(min = 2018) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatPentecostIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.PENTECOST");
  }

  @Property
  void ensuresThatWhitMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ROMANIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.WHIT_MONDAY");
  }

}
