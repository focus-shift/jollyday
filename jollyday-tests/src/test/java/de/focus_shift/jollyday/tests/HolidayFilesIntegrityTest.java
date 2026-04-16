package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

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
}
