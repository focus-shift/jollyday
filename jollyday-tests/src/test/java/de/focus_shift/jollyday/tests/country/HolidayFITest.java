package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.FINLAND;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayFITest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "fi";
  private static final int YEAR = 2010;

  @BeforeEach
  void setUp() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
  }

  @Test
  void testManagerFIStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Property
  void ensuresThatNewYearIsOnFirstJanuary(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatEpiphanyConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 6), "EPIPHANY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatDayOfIndependenceIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 6), "INDEPENDENCE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatOrthodoxChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 24), "CHRISTMAS_EVE", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensuresThatGoodFridayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatAscensionDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.ASCENSION_DAY");
  }

  @Property
  void ensuresThatSelfGovernanceIsConfiguredInAland(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year, "01");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 9), "SELF_GOVERNANCE", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatWhitSundayIsConfiguredInAland(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year, "01");
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.WHIT_SUNDAY");
  }
}
