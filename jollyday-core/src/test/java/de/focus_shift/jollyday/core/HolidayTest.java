package de.focus_shift.jollyday.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.HolidayType.UNOFFICIAL_HOLIDAY;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
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

  @Test
  void testHolidayDescription() {
    Locale.setDefault(ENGLISH);

    final Holiday holiday = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(holiday.getDescription()).isEqualTo("Christmas");
    assertThat(holiday.getDescription(GERMAN)).isEqualTo("Weihnachten");
    assertThat(holiday.getDescription(new Locale("nl"))).isEqualTo("Kerstmis");
  }

  @Test
  void testHolidayEquals() {
    final Holiday h1 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isEqualTo(h1);

    final Holiday h2b = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isEqualTo(h2b);

    final Holiday h2 = new Holiday(LocalDate.of(2011, 2, 1), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h2);

    final Holiday h3 = new Holiday(LocalDate.of(2011, 2, 2), "NEW_YEAR", OFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h3);

    final Holiday h4 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", UNOFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h4);
  }
}
