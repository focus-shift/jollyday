package de.focus_shift.parser.functions;

import de.focus_shift.spi.FixedWeekdayBetweenFixed;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;

/**
 * @author sdiedrichsen
 * @version $
 * @since 12.03.20
 */
public class FindWeekDayBetween implements Function<FixedWeekdayBetweenFixed, LocalDate> {

  private final LocalDate from;
  private final LocalDate to;

  public FindWeekDayBetween(LocalDate from, LocalDate to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public LocalDate apply(FixedWeekdayBetweenFixed fwm) {
    LocalDate current = from;

    final Iterator<LocalDate> iterator = new Iterator<>() {
      @Override
      public boolean hasNext() {
        return !current.isAfter(to);
      }

      @Override
      public LocalDate next() {
        return current.plusDays(1);
      }
    };

    return StreamSupport.stream(spliteratorUnknownSize(iterator, ORDERED | IMMUTABLE), false)
      .filter(date -> date.getDayOfWeek() == fwm.weekday())
      .findFirst().orElse(null);
  }
}
