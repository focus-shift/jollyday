package de.jollyday;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HolidayTest {

  @Test
  public void testComparable() {
    Holiday holiday = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    assertTrue(holiday instanceof Comparable, "Holiday does not implement the Comparable interface.");
  }

  @Test
  public void testCompareToLess() {
    Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = newYear.compareTo(christmas);
    assertTrue(actual < 0, "Wrong holiday comparator value for less.");
  }

  @Test
  public void testCompareToGreater() {
    Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, HolidayType.OFFICIAL_HOLIDAY);
    Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = christmas.compareTo(newYear);
    assertTrue(actual > 0, "Wrong holiday comparator value for greater.");
  }

  @Test
  public void testCompareToEqual() {
    Holiday firstDayOfYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, HolidayType.OFFICIAL_HOLIDAY);
    int actual = firstDayOfYear.compareTo(newYear);
    assertTrue(actual == 0, "Wrong holiday comparator for equal.");
  }
}
