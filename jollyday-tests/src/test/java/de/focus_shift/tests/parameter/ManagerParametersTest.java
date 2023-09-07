package de.focus_shift.tests.parameter;

import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.OCTOBER;
import static java.util.Locale.GERMANY;
import static org.assertj.core.api.Assertions.assertThat;

class ManagerParametersTest {

  @Test
  void testCreateParameterFromLocale() {
    final ManagerParameter params = ManagerParameters.create(GERMANY);
    final HolidayManager manager = HolidayManager.getInstance(params);
    assertThat(manager.getCalendarHierarchy().getId()).isEqualTo(GERMANY.getCountry().toLowerCase());

    final Calendar thirdOfOctober = Calendar.getInstance();
    thirdOfOctober.set(MONTH, OCTOBER);
    thirdOfOctober.set(DAY_OF_MONTH, 3);
    assertThat(manager.isHoliday(thirdOfOctober)).isTrue();
  }
}
