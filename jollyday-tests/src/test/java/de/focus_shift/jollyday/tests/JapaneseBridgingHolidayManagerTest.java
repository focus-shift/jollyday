package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.impl.JapaneseBridgingHolidayManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

class JapaneseBridgingHolidayManagerTest {

  @BeforeAll
  static void beforeAll() {
    System.setProperty("de.focus_shift.jollyday.config.urls", "file:./src/test/resources/jollyday-japanese.properties");
  }

  @AfterAll
  static void afterAll() {
    System.clearProperty("de.focus_shift.jollyday.config.urls");
  }

  @Test
  void ensureToInstantiateHolidayManagerImplementationBasedOnCountry() {
    assertThat(HolidayManager.getInstance(create("test_jp"))).isInstanceOf(JapaneseBridgingHolidayManager.class);
  }

  @Test
  void ensureThatBridgingDaysAreCalculatesCorrectly() {
    final HolidayManager japaneseHolidayManager = HolidayManager.getInstance(create("test_jp"));
    assertThat(japaneseHolidayManager).isInstanceOf(JapaneseBridgingHolidayManager.class);

    final Set<Holiday> holidaysWithBridging = japaneseHolidayManager.getHolidays(Year.of(2000));
    assertThat(holidaysWithBridging)
      .isNotNull()
      .hasSize(6)
      .containsOnly(
        new Holiday(LocalDate.of(2000, 1, 1), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2000, 1, 2), "BRIDGING_HOLIDAY", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2000, 1, 3), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2000, 1, 4), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2000, 1, 5), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(2000, 1, 8), "NEW_YEAR", PUBLIC_HOLIDAY)
      );
  }
}
