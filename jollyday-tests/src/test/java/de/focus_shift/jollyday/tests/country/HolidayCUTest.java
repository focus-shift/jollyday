package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.CUBA;
import static de.focus_shift.jollyday.core.HolidayCalendar.FINLAND;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.MAY;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayCUTest extends AbstractCountryTestBase {

  @Property
  void ensuresThatTriumphOfTheRevolutionIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "TRIUMPH_OF_THE_REVOLUTION", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatVictoryOfFidelCastroIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 2), "VICTORY_OF_FIDEL_CASTRO", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatLabourDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MAY, 1), "LABOUR_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatCommemorationOfTheAssaultOfTheMancadaGarrisonNotConfiguredBefore2016(@ForAll @YearRange(max = 2015) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JULY, 25), "DAY_BEFORE_THE_COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), JULY, 26), "COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), JULY, 27), "DAY_AFTER_THE_COMMEMORATION_OF_THE_ASSAULT_OF_THE_MONCADA_GARRISON", OFFICIAL_HOLIDAY));

  }

  @Property
  void ensuresThatIndependenceDayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), OCTOBER, 10), "INDEPENDENCE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(CUBA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatGoodFridayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(FINLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }
}
