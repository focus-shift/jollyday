package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Described;

import java.time.LocalDate;
import java.util.function.Function;

public class CreateHoliday implements Function<Described, Holiday> {

  private final LocalDate localDate;
  private final String propertiesKey;

  public CreateHoliday(LocalDate localDate) {
    this(localDate, null);
  }

  public CreateHoliday(LocalDate localDate, String propertiesKey) {
    this.localDate = localDate;
    this.propertiesKey = propertiesKey;
  }

  @Override
  public Holiday apply(final Described described) {
    return new Holiday(localDate, propertiesKey == null ? described.descriptionPropertiesKey() : propertiesKey, described.officiality());
  }
}
