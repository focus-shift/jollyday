package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.IMMUTABLE;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;

public class FindWeekDayBetween implements Function<FixedWeekdayBetweenFixed, LocalDate>, Iterable<LocalDate> {

  private final LocalDate from;
  private final LocalDate to;

  public FindWeekDayBetween(final LocalDate from, final LocalDate to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public LocalDate apply(final FixedWeekdayBetweenFixed fwm) {
    return StreamSupport.stream(spliteratorUnknownSize(iterator(), ORDERED | IMMUTABLE), false)
      .filter(date -> date.getDayOfWeek() == fwm.weekday())
      .findFirst().orElse(null);
  }

  @Override
  public Iterator<LocalDate> iterator() {
    return new FindWeekDayBetweenIterator(from, to);
  }

  private static final class FindWeekDayBetweenIterator implements Iterator<LocalDate> {

    private LocalDate cursor;
    private final LocalDate endDate;

    FindWeekDayBetweenIterator(final LocalDate startDate, final LocalDate endDate) {
      this.cursor = startDate;
      this.endDate = endDate;
    }

    @Override
    public boolean hasNext() {
      return cursor.isBefore(endDate) || cursor.isEqual(endDate);
    }

    @Override
    public LocalDate next() {
      if (!hasNext()) {
        throw new NoSuchElementException("next date is after endDate which is not in range anymore.");
      }

      final LocalDate current = cursor;
      cursor = cursor.plusDays(1);
      return current;
    }
  }
}
