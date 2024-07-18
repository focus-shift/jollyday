package de.focus_shift.jollyday.core.parser.functions;

import org.junit.jupiter.api.Test;
import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.IsoChronology;
import java.time.chrono.MinguoChronology;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateEasterSundayTest {

  @Test
  void ensureThatJulianEasterCalculationWasChosenByJulianChronology() {
    final LocalDate julianEasterSunday = new CalculateEasterSunday(Year.of(2005)).apply(JulianChronology.INSTANCE);
    assertThat(julianEasterSunday).isEqualTo(LocalDate.of(2005, 5, 1));
  }

  @Test
  void ensureThatGregorianEasterCalculationWasChosenByGregorianChronology() {
    final LocalDate gregorianEasterSunday = new CalculateEasterSunday(Year.of(2005)).apply(IsoChronology.INSTANCE);
    assertThat(gregorianEasterSunday).isEqualTo(LocalDate.of(2005, 3, 27));
  }

  @Test
  void ensureThatGregorianEasterCalculationWasChosenByUnknownChronologySince1584() {
    final LocalDate gregorianEasterSunday = new CalculateEasterSunday(Year.of(1584)).apply(MinguoChronology.INSTANCE);
    assertThat(gregorianEasterSunday).isEqualTo(LocalDate.of(1584, 4, 1));
  }

  @Test
  void ensureThatJulianEasterCalculationWasChosenByUnknownChronologyUntil1583() {
    final LocalDate julianEasterSunday = new CalculateEasterSunday(Year.of(1583)).apply(MinguoChronology.INSTANCE);
    assertThat(julianEasterSunday).isEqualTo(LocalDate.of(1583, 4, 10));
  }
}
