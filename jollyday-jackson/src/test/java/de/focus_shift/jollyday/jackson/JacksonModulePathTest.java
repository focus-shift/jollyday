package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Ensures that jollyday-core can evaluate holidays loaded by jollyday-jackson when both run on the module path.
 * See <a href="https://github.com/focus-shift/jollyday/issues/1539">#1539</a>.
 */
class JacksonModulePathTest {

  @Test
  void ensureHolidaysCanBeEvaluatedOnTheModulePath() {
    assertThat(JacksonHolidays.class.getModule().getName()).isEqualTo("de.focus_shift.jollyday.jackson");
    assertThat(HolidayManager.class.getModule().getName()).isEqualTo("de.focus_shift.jollyday.core");

    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));

    assertThat(holidayManager.isHoliday(LocalDate.of(2026, 1, 1))).isTrue();
  }
}
