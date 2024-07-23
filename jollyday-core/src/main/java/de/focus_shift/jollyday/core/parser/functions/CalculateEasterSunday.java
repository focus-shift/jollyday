package de.focus_shift.jollyday.core.parser.functions;

import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.function.Function;

public class CalculateEasterSunday implements Function<Chronology, LocalDate> {

  private final Year year;

  public CalculateEasterSunday(final Year year) {
    this.year = year;
  }

  @Override
  public LocalDate apply(Chronology chronology) {
    if (chronology == JulianChronology.INSTANCE) {
      return new CalculateJulianEasterSunday().apply(year);
    } else if (chronology == IsoChronology.INSTANCE) {
      return new CalculateGregorianEasterSunday().apply(year);
    } else if (year.isAfter(Year.of(1583))) {
      return new CalculateGregorianEasterSunday().apply(year);
    } else {
      return new CalculateJulianEasterSunday().apply(year);
    }
  }
}
