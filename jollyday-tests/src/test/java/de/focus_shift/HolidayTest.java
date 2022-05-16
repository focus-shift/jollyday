package de.focus_shift;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayTest {

  @Test
  void testComparable() {
    final Holiday holiday = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    assertTrue(holiday instanceof Comparable, "Holiday does not implement the Comparable interface.");
  }

  @Test
  void testCompareToLess() {
    final Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    final Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = newYear.compareTo(christmas);
    assertTrue(actual < 0, "Wrong holiday comparator value for less.");
  }

  @Test
  void testCompareToGreater() {
    final Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, HolidayType.OFFICIAL_HOLIDAY);
    final Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = christmas.compareTo(newYear);
    assertTrue(actual > 0, "Wrong holiday comparator value for greater.");
  }

  @Test
  void testCompareToEqual() {
    Holiday firstDayOfYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = firstDayOfYear.compareTo(newYear);
    assertTrue(actual == 0, "Wrong holiday comparator for equal.");
  }
}
