package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

public class FindWeekDayBetween implements Function<FixedWeekdayBetweenFixed, LocalDate> {

  private final LocalDate from;
  private final LocalDate to;

  public FindWeekDayBetween(final LocalDate from, final LocalDate to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public LocalDate apply(final FixedWeekdayBetweenFixed fwm) {
    return Stream.iterate(from, date -> date.plusDays(1))
      .limit(DAYS.between(from, to) + 1)
      .filter(date -> date.getDayOfWeek() == fwm.weekday())
      .findFirst().orElse(null);
  }
}
