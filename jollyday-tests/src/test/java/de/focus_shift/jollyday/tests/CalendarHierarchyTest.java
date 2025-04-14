package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static de.focus_shift.jollyday.core.HolidayManager.getInstance;
import static de.focus_shift.jollyday.core.ManagerParameters.create;

class CalendarHierarchyTest {

  @Test
  void name() {
    Arrays.stream(HolidayCalendar.values()).forEach(holidayCalendar -> {
      System.out.println(getInstance(create(holidayCalendar)).getCalendarHierarchy());
    });
  }
}
