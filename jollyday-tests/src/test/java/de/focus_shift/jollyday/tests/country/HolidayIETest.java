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

import static de.focus_shift.jollyday.core.HolidayCalendar.IRELAND;
import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayIETest extends AbstractCountryTestBase {

  @Property
  void ensuresThatNewYearConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStPatrickConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), MARCH, 17), "ST_PATRICK", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatChristmasIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 25), "CHRISTMAS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatStephensIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 26), "STEPHENS", PUBLIC_HOLIDAY));
  }

  @Property
  void ensuresThatEasterIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER");
  }

  @Property
  void ensuresThatEasterMondayIsConfigured(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("christian.EASTER_MONDAY");
  }

  @Property
  void ensureStBrigidNotConfiguredBefore2023(@ForAll @YearRange(max = 2022) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("ST_BRIGID");
  }

  @Property
  void ensureStBrigidIsConfiguredFrom2023(@ForAll @YearRange(min = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(IRELAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year);
    assertThat(holidays)
      .isNotEmpty()
      .extracting(Holiday::getPropertiesKey)
      .contains("ST_BRIGID");
  }

  @Test
  void testStBrigidsDayOn2023() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2023 = holidayManagerIE.getHolidays(Year.of(2023));
    assertThat(holidays2023)
      .contains(new Holiday(LocalDate.of(2023, FEBRUARY, 6), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2024() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2024 = holidayManagerIE.getHolidays(Year.of(2024));
    assertThat(holidays2024)
      .contains(new Holiday(LocalDate.of(2024, FEBRUARY, 5), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2025() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2025 = holidayManagerIE.getHolidays(Year.of(2025));
    assertThat(holidays2025)
      .contains(new Holiday(LocalDate.of(2025, FEBRUARY, 3), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2026() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2026 = holidayManagerIE.getHolidays(Year.of(2026));
    assertThat(holidays2026)
      .contains(new Holiday(LocalDate.of(2026, FEBRUARY, 2), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2027() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2027 = holidayManagerIE.getHolidays(Year.of(2027));
    assertThat(holidays2027)
      .contains(new Holiday(LocalDate.of(2027, FEBRUARY, 1), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2028() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2028 = holidayManagerIE.getHolidays(Year.of(2028));
    assertThat(holidays2028)
      .contains(new Holiday(LocalDate.of(2028, FEBRUARY, 7), "ST_BRIGID", PUBLIC_HOLIDAY));
  }

  @Test
  void testStBrigidsDayOn2030() {
    final HolidayManager holidayManagerIE = HolidayManager.getInstance(create(IRELAND));

    final Set<Holiday> holidays2030 = holidayManagerIE.getHolidays(Year.of(2030));
    assertThat(holidays2030)
      .contains(new Holiday(LocalDate.of(2030, FEBRUARY, 1), "ST_BRIGID", PUBLIC_HOLIDAY));
  }
}
