package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.spi.Described;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

class DescribedDateHolder {

  private final LocalDate actualDate;
  private final LocalDate observedDate;
  private final Described described;

  DescribedDateHolder(@NonNull final Described described, @NonNull final LocalDate actualDate) {
    this(described, actualDate, null);
  }

  DescribedDateHolder(@NonNull final Described described, @NonNull final LocalDate actualDate, @Nullable final LocalDate observedDate) {
    this.actualDate = actualDate;
    this.observedDate = observedDate;
    this.described = described;
  }

  public @NonNull LocalDate getActualDate() {
    return actualDate;
  }

  public @Nullable LocalDate getObservedDate() {
    return observedDate;
  }

  public @NonNull Described getDescribed() {
    return described;
  }
}
