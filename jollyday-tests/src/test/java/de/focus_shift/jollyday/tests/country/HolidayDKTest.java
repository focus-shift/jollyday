package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.DENMARK;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayDKTest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearIsOnFirstJanuary(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(DENMARK));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatFirstAndSecondChristmasIsCorrect(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(DENMARK));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", OFFICIAL_HOLIDAY))
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", OFFICIAL_HOLIDAY));
  }

  @Test
  void ensuresThatGeneralPrayersDayIsOnlyUntil2023() {
    final HolidayManager holidayManagerDK = HolidayManager.getInstance(create(DENMARK));

    final Set<Holiday> holidays2023 = holidayManagerDK.getHolidays(2023);
    assertThat(holidays2023)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(2023, MAY, 5), "christian.GENERAL_PRAYER_DAY", OFFICIAL_HOLIDAY));

    final Set<Holiday> holidays2024 = holidayManagerDK.getHolidays(2024);
    assertThat(holidays2024)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("christian.GENERAL_PRAYER_DAY");
  }
}
