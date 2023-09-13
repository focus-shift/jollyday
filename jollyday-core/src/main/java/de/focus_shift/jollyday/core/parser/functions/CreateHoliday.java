package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Described;

import java.time.LocalDate;
import java.util.function.Function;

public class CreateHoliday implements Function<Described, Holiday> {

  private final LocalDate localDate;

  public CreateHoliday(LocalDate localDate) {
    this.localDate = localDate;
  }

  @Override
  public Holiday apply(Described described) {
    return new Holiday(localDate, described.descriptionPropertiesKey(), described.officiality());
  }
}
