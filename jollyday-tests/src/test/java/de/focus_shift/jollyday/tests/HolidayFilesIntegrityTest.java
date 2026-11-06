package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashSet;
import java.util.Set;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayFilesIntegrityTest {

  @Test
  void ensureNoDuplicateHierarchyValues() {
    final Set<String> hierarchies = new HashSet<>();
    final Set<String> duplicates = new HashSet<>();

    for (HolidayCalendar calendar : HolidayCalendar.values()) {
      final HolidayManager holidayManager = HolidayManager.getInstance(create(calendar));
      final String hierarchyId = holidayManager.getCalendarHierarchy().getId();

      if (!hierarchies.add(hierarchyId)) {
        duplicates.add(hierarchyId);
      }
    }

    assertThat(duplicates)
      .withFailMessage("Found duplicate hierarchy values: %s", duplicates)
      .isEmpty();
  }

  @ParameterizedTest
  @EnumSource(HolidayCalendar.class)
  void ensureCalendarCanBeReloadedByItsHierarchyId(final HolidayCalendar calendar) {
    final String hierarchyId = HolidayManager.getInstance(create(calendar)).getCalendarHierarchy().getId();

    // loading by the id the library reports must find the same calendar file again
    final HolidayManager reloaded = HolidayManager.getInstance(create(hierarchyId));

    assertThat(reloaded.getCalendarHierarchy().getId()).isEqualTo(hierarchyId);
  }
}
