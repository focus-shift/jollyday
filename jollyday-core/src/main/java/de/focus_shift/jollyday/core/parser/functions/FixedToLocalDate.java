package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.Fixed;

import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

public class FixedToLocalDate implements Function<Fixed, LocalDate> {

  private final Year year;

  public FixedToLocalDate(final Year year) {
    this.year = year;
  }

  @Override
  public LocalDate apply(final Fixed fixed) {
    return fixed.day().atYear(year.getValue());
  }
}
