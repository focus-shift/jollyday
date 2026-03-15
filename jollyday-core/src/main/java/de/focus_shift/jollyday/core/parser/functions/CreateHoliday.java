package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Described;
import java.time.LocalDate;
import java.util.function.Function;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CreateHoliday implements Function<Described, Holiday> {

  private final LocalDate actualDate;
  private final LocalDate observedDate;
  private final String propertiesKey;

  public CreateHoliday(@NonNull final LocalDate actualDate) {
    this(actualDate, null, null);
  }

  public CreateHoliday(
      @NonNull final LocalDate actualDate, @Nullable final LocalDate observedDate) {
    this(actualDate, observedDate, null);
  }

  public CreateHoliday(
      @NonNull final LocalDate actualDate,
      @Nullable final LocalDate observedDate,
      @Nullable final String propertiesKey) {
    this.actualDate = actualDate;
    this.observedDate = observedDate;
    this.propertiesKey = propertiesKey;
  }

  @Override
  public @NonNull Holiday apply(@NonNull final Described described) {
    return new Holiday(
        actualDate,
        observedDate,
        propertiesKey == null ? described.descriptionPropertiesKey() : propertiesKey,
        described.holidayType());
  }
}
