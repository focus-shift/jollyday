package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static de.focus_shift.HolidayCalendar.UNITED_KINGDOM;
import static de.focus_shift.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.ManagerParameters.create;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayGBTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "gb";


  @Test
  void ensuresThatKingsCoronationForKingCharlesIIIIn2023() {
    final HolidayManager holidayManagerGB = HolidayManager.getInstance(create(UNITED_KINGDOM));

    final Set<Holiday> holidays2022 = holidayManagerGB.getHolidays(2022);
    assertThat(holidays2022)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("KINGS_CORONATION");

    final Set<Holiday> holidays2023 = holidayManagerGB.getHolidays(2023);
    assertThat(holidays2023)
      .contains(new Holiday(LocalDate.of(2023, MAY, 8), "KINGS_CORONATION", OFFICIAL_HOLIDAY));

    final Set<Holiday> holidays2024 = holidayManagerGB.getHolidays(2024);
    assertThat(holidays2024)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("KINGS_CORONATION");
  }

  @ParameterizedTest
  @ValueSource(ints = {2010, 2022, 2023})
  void testManagerGBStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerGBChristmasMovingDaysWhenChristmasOnSunday() {
    doChristmasContainmentTest(2011, 26, 27);
  }

  @Test
  void testManagerGBChristmasMovingDaysWhenChristmasOnSaturday() {
    doChristmasContainmentTest(2010, 27, 28);
  }

  @Test
  void testManagerGBChristmasMovingDaysWhenChristmasOnFriday() {
    doChristmasContainmentTest(2009, 25, 28);
  }

  private void doChristmasContainmentTest(int year, int dayOfChristmas, int dayOfBoxingday) {
    final LocalDate christmas = LocalDate.of(year, 12, dayOfChristmas);
    final LocalDate boxingday = LocalDate.of(year, 12, dayOfBoxingday);
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(UNITED_KINGDOM));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(contains(christmas, holidays)).isTrue();
    assertThat(contains(boxingday, holidays)).isTrue();
  }

  private boolean contains(LocalDate localDate, Set<Holiday> holidays) {
    for (Holiday h : holidays) {
      if (localDate.equals(h.getDate())) {
        return true;
      }
    }
    return false;
  }
}
