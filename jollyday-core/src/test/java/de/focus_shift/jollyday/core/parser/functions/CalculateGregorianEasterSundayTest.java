package de.focus_shift.jollyday.core.parser.functions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateGregorianEasterSundayTest {
  @ParameterizedTest
  @CsvSource({
    "2001,2001-04-15",
    "2005,2005-03-27",
    "2010,2010-04-04",
    "2017,2017-04-16",
    "2025,2025-04-20",
  })
  void calculateEasterSunday(Year year, LocalDate expectedEasterSunday) {
    final LocalDate easterSunday = new CalculateGregorianEasterSunday().apply(year);
    assertThat(easterSunday).isEqualTo(expectedEasterSunday);
  }
}
