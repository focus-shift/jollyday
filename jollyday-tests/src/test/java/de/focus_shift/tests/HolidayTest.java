package de.focus_shift.tests;

import de.focus_shift.Holiday;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static de.focus_shift.HolidayType.OFFICIAL_HOLIDAY;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayTest {

  @Test
  void testComparable() {
    final Holiday holiday = new Holiday(LocalDate.of(2015, 1, 1), null, OFFICIAL_HOLIDAY);
    assertThat(holiday).isInstanceOf(Comparable.class);
  }

  @Test
  void testCompareToLess() {
    final Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, OFFICIAL_HOLIDAY);
    final Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, OFFICIAL_HOLIDAY);
    assertThat(newYear).isLessThan(christmas);
  }

  @Test
  void testCompareToGreater() {
    final Holiday christmas = new Holiday(LocalDate.of(2015, 12, 25), null, OFFICIAL_HOLIDAY);
    final Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, OFFICIAL_HOLIDAY);
    assertThat(christmas).isGreaterThan(newYear);
  }

  @Test
  void testCompareToEqual() {
    final Holiday firstDayOfYear = new Holiday(LocalDate.of(2015, 1, 1), null, OFFICIAL_HOLIDAY);
    final Holiday newYear = new Holiday(LocalDate.of(2015, 1, 1), null, OFFICIAL_HOLIDAY);
    assertThat(firstDayOfYear).isEqualByComparingTo(newYear);
  }
}
