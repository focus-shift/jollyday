package de.focus_shift.jollyday.core.parser.functions;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.HijrahChronology;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class CalculateRelativeDatesFromChronologyWithinGregorianYearTest {

  @Test
  void testCalendarIslamicNewYear() {
    final Stream<LocalDate> holidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, HijrahChronology.INSTANCE, 0).apply(Year.of(2008));

    assertThat(holidays)
      .containsExactly(LocalDate.of(2008, JANUARY, 10), LocalDate.of(2008, DECEMBER, 29));
  }

  @Test
  void testCalendarIslamicAschura2008() {
    final Stream<LocalDate> holidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HijrahChronology.INSTANCE, 0).apply(Year.of(2008));

    assertThat(holidays)
      .containsExactly(LocalDate.of(2008, JANUARY, 19));
  }

  @Test
  void testCalendarIslamicAschura2009() {
    final Stream<LocalDate> holidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HijrahChronology.INSTANCE, 0).apply(Year.of(2009));

    assertThat(holidays)
      .containsExactly(LocalDate.of(2009, JANUARY, 7), LocalDate.of(2009, DECEMBER, 27));
  }

  @Test
  void testCalendarIslamicIdAlFitr2008() {
    final Stream<LocalDate> holidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, 0).apply(Year.of(2008));

    assertThat(holidays)
      .containsExactly(LocalDate.of(2008, OCTOBER, 1));
  }

  @Test
  void testCalendarIslamicIdAlFitr2009() {
    final Stream<LocalDate> holidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, 0).apply(Year.of(2009));

    assertThat(holidays)
      .containsExactly(LocalDate.of(2009, SEPTEMBER, 20));
  }
}
