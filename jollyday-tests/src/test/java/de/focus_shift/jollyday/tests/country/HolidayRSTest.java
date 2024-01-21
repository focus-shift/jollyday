package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.SERBIA;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayRSTest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearFirstIsConfiguredAndIfOnSundayMovesToMondayAndTuesday(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());

    final LocalDate firstNewYear = LocalDate.of(year.getValue(), JANUARY, 1);
    final LocalDate secondNewYear = LocalDate.of(year.getValue(), JANUARY, 2);
    checkSundayMovingToNextWorkday(holidays, firstNewYear, secondNewYear, "NEW_YEAR", OFFICIAL_HOLIDAY);
  }

  @Property
  void ensuresThatStateHoodIsConfiguredAndIfOnSundayMovesToMondayAndTuesday(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());

    final LocalDate firstStateHood = LocalDate.of(year.getValue(), FEBRUARY, 15);
    final LocalDate secondStateHood = LocalDate.of(year.getValue(), FEBRUARY, 16);
    checkSundayMovingToNextWorkday(holidays, firstStateHood, secondStateHood, "STATEHOOD", OFFICIAL_HOLIDAY);
  }

  @Property
  void ensuresThatLabourDayIsConfiguredAndIfOnSundayMovesToMondayAndTuesday(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());

    final LocalDate firstLabourDay = LocalDate.of(year.getValue(), MAY, 1);
    final LocalDate secondLabourDay = LocalDate.of(year.getValue(), MAY, 2);
    checkSundayMovingToNextWorkday(holidays, firstLabourDay, secondLabourDay, "LABOUR_DAY", OFFICIAL_HOLIDAY);
  }

  @Property
  void ensuresThatArmisticeIsConfiguredAndIfOnSundayMovesToMonday(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());

    final LocalDate armistice = LocalDate.of(year.getValue(), NOVEMBER, 11);
    if (isSunday(armistice)) {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 12), "ARMISTICE", OFFICIAL_HOLIDAY));
    } else {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(LocalDate.of(year.getValue(), NOVEMBER, 11), "ARMISTICE", OFFICIAL_HOLIDAY));
    }
  }

  @Property
  void ensuresThatOrthodoxChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 7), "CHRISTMAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatGoodFridayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.GOOD_FRIDAY");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SERBIA));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  private static boolean isSunday(final LocalDate ld) {
    return DayOfWeek.of(ld.get(DAY_OF_WEEK)) == SUNDAY;
  }

  private static void checkSundayMovingToNextWorkday(Set<Holiday> holidays, LocalDate firstDay, LocalDate secondDay, String descriptionPropertiesKey, HolidayType holidayType) {
    if (isSunday(firstDay)) {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(secondDay, descriptionPropertiesKey, holidayType))
        .contains(new Holiday(firstDay.plusDays(2), descriptionPropertiesKey, holidayType));
    } else if (isSunday(secondDay)) {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(firstDay, descriptionPropertiesKey, holidayType))
        .contains(new Holiday(secondDay.plusDays(1), descriptionPropertiesKey, holidayType));
    } else {
      assertThat(holidays)
        .isNotEmpty()
        .contains(new Holiday(firstDay, descriptionPropertiesKey, holidayType))
        .contains(new Holiday(secondDay, descriptionPropertiesKey, holidayType));
    }
  }
}
