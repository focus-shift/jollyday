package de.focus_shift.jollyday.core.parser.functions;

import static java.time.temporal.ChronoUnit.DAYS;

import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class FindWeekDayBetween
    implements Function<FixedWeekdayBetweenFixedHolidayConfiguration, LocalDate> {

  private final LocalDate from;
  private final LocalDate to;

  public FindWeekDayBetween(@NonNull final LocalDate from, @NonNull final LocalDate to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public @Nullable LocalDate apply(
      @NonNull final FixedWeekdayBetweenFixedHolidayConfiguration fwm) {
    return Stream.iterate(from, date -> date.plusDays(1))
        .limit(DAYS.between(from, to) + 1)
        .filter(date -> date.getDayOfWeek() == fwm.weekday())
        .findFirst()
        .orElse(null);
  }
}
