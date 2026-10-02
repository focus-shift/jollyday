package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Ensures that jollyday-core can evaluate holidays loaded by jollyday-jaxb when both run on the module path.
 * See <a href="https://github.com/focus-shift/jollyday/issues/1539">#1539</a>.
 */
class JaxbModulePathTest {

  // set by the CI build for jollyday-tests, its relative file URL cannot be resolved in this module
  private static final String CONFIG_URLS_PROPERTY = "de.focus_shift.jollyday.config.urls";

  private String configUrls;

  @BeforeEach
  void setUp() {
    configUrls = System.clearProperty(CONFIG_URLS_PROPERTY);
  }

  @AfterEach
  void tearDown() {
    if (configUrls != null) {
      System.setProperty(CONFIG_URLS_PROPERTY, configUrls);
    }
  }

  @Test
  void ensureHolidaysCanBeEvaluatedOnTheModulePath() {
    assertThat(JaxbHolidays.class.getModule().getName()).isEqualTo("de.focus_shift.jollyday.jaxb");
    assertThat(HolidayManager.class.getModule().getName()).isEqualTo("de.focus_shift.jollyday.core");

    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));

    assertThat(holidayManager.isHoliday(LocalDate.of(2026, 1, 1))).isTrue();
  }
}
