package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.impl.JapaneseBridgingHolidayManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;
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

  @ParameterizedTest
  @ValueSource(ints = {1986, 2006, 2011})
  void ensureThatBridgingDaysAreCalculatesCorrectly(final int year) {
    final HolidayManager japaneseHolidayManager = HolidayManager.getInstance(create("test_jp"));
    assertThat(japaneseHolidayManager).isInstanceOf(JapaneseBridgingHolidayManager.class);

    final Set<Holiday> holidaysWithBridging = japaneseHolidayManager.getHolidays(Year.of(year));
    assertThat(holidaysWithBridging)
      .isNotNull()
      .hasSize(6)
      .containsOnly(
        new Holiday(LocalDate.of(year, Month.JANUARY, 1), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(year, Month.JANUARY, 2), "BRIDGING_HOLIDAY", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(year, Month.JANUARY, 3), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(year, Month.JANUARY, 4), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(year, Month.JANUARY, 5), "NEW_YEAR", PUBLIC_HOLIDAY),
        new Holiday(LocalDate.of(year, Month.JANUARY, 8), "NEW_YEAR", PUBLIC_HOLIDAY)
      );
  }

  @ParameterizedTest
  @ValueSource(ints = {
    1985, // before the citizens' holiday was in force on 27 December 1985
    2000  // 2 January 2000 is a Sunday, which could not be a citizens' holiday before 2007
  })
  void ensureThatNoBridgingDayIsCalculated(final int year) {
    final HolidayManager japaneseHolidayManager = HolidayManager.getInstance(create("test_jp"));

    final Set<Holiday> holidays = japaneseHolidayManager.getHolidays(Year.of(year));
    assertThat(holidays)
      .hasSize(5)
      .extracting(Holiday::getPropertiesKey)
      .doesNotContain("BRIDGING_HOLIDAY");
  }
}
