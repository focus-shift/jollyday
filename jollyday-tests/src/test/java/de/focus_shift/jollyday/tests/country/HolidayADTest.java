package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.ANDORRA;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayADTest {

  @Property
  void ensuresThatNewYearIsOnFirstJanuary(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEpiphanyConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 6), "EPIPHANY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatConstitutionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MARCH, 14), "CONSTITUTION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAssumptionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 15), "ASSUMPTION_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatNationalDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 8), "NATIONAL_DAY", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatAllSaintsIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 1), "ALL_SAINTS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatImmaculateConceptionIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 8), "IMMACULATE_CONCEPTION", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatCarnivalIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.CARNIVAL");
  }

  @Property
  void ensuresThatGoodFridayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatWhitMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ANDORRA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.WHIT_MONDAY");
  }
}
