package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.MOROCCO;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.AUGUST;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayMATest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatProclamationOfIndependenceIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 11), "PROCLAMATION_OF_INDEPENDENCE", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatAmazighNewYearIsConfigured(@ForAll @YearRange(min = 2024, max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 14), "AMAZIGH_NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatAmazighNewYearIsNotConfiguredUntil2023(@ForAll @YearRange(max = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), JANUARY, 14), "AMAZIGH_NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatEnthronementIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JULY, 30), "ENTHRONEMENT", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatZikraQuedEdDahanIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 14), "ZIKRA_OUED_ED_DAHAB", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatRevolutionOfTheKingAndThePeopleIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 20), "REVOLUTION_OF_THE_KING_AND_THE_PEOPLE", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatYouthIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), AUGUST, 21), "YOUTH", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatGreenMarchIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 6), "GREEN_MARCH", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatIndependenceDayIsConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 18), "INDEPENDENCE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatIslamicHolidaysAreConfigured(@ForAll @YearRange(max = 2150) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(MOROCCO));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("islamic.NEWYEAR", "islamic.ID_AL_FITR", "islamic.ID_UL_ADHA", "islamic.MAWLID_AN_NABI");
  }
}
