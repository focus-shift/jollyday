package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.WeekendConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.Weekend;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Optional;

/**
 * see {@link WeekendConfiguration}
 */
class JaxbWeekend implements WeekendConfiguration {

  private final Weekend weekend;

  JaxbWeekend(Weekend weekend) {
    this.weekend = weekend;
  }

  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(weekend.getWeekday().name());
  }

  @Override
  public @NonNull Optional<Year> validFrom() {
    return weekend.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(weekend.getValidFrom()));
  }

  @Override
  public @NonNull Optional<Year> validTo() {
    return weekend.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(weekend.getValidTo()));
  }
}
