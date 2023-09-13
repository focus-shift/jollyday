package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.Fixed;

import java.time.LocalDate;
import java.util.function.Function;

public class FixedToLocalDate implements Function<Fixed, LocalDate> {

  private final int year;

  public FixedToLocalDate(int year) {
    this.year = year;
  }

  @Override
  public LocalDate apply(Fixed fixed) {
    return fixed.day().atYear(year);
  }
}
