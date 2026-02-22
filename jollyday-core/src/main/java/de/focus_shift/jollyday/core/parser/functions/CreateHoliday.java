package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Described;

import java.time.LocalDate;
import java.util.function.Function;

public class CreateHoliday implements Function<Described, Holiday> {

  private final LocalDate actualDate;
  private final LocalDate observedDate;
  private final String propertiesKey;

  public CreateHoliday(final LocalDate actualDate) {
    this(actualDate, null, null);
  }

  public CreateHoliday(final LocalDate actualDate, final LocalDate observedDate) {
    this(actualDate, observedDate, null);
  }

  public CreateHoliday(final LocalDate actualDate, final LocalDate observedDate, final String propertiesKey) {
    this.actualDate = actualDate;
    this.observedDate = observedDate;
    this.propertiesKey = propertiesKey;
  }

  @Override
  public Holiday apply(final Described described) {
    return new Holiday(actualDate, observedDate, propertiesKey == null ? described.descriptionPropertiesKey() : propertiesKey, described.holidayType());
  }
}
