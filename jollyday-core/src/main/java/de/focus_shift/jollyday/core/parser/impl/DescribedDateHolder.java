package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.spi.Described;

import java.time.LocalDate;

class DescribedDateHolder {

  private final LocalDate actualDate;
  private final LocalDate observedDate;
  private final Described described;

  DescribedDateHolder(final Described described, final LocalDate actualDate) {
    this(described, actualDate, null);
  }

  DescribedDateHolder(final Described described, final LocalDate actualDate, final LocalDate observedDate) {
    this.actualDate = actualDate;
    this.observedDate = observedDate;
    this.described = described;
  }

  public LocalDate getActualDate() {
    return actualDate;
  }

  public LocalDate getObservedDate() {
    return observedDate;
  }

  public Described getDescribed() {
    return described;
  }
}
