package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.spi.Described;

import java.time.LocalDate;

class DescribedDateHolder {

  private final LocalDate date;
  private final LocalDate observedDate;
  private final Described described;

  DescribedDateHolder(final Described described, final LocalDate date) {
    this(described, date, date);
  }

  DescribedDateHolder(final Described described, final LocalDate date, final LocalDate observedDate) {
    this.date = date;
    this.observedDate = observedDate;
    this.described = described;
  }

  public LocalDate getDate() {
    return date;
  }

  public LocalDate getObservedDate() {
    return observedDate;
  }

  public Described getDescribed() {
    return described;
  }
}
