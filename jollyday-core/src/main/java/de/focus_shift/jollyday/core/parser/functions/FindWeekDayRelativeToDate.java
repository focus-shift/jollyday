package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Relation;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previous;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class FindWeekDayRelativeToDate implements Function<FixedWeekdayRelativeToFixed, LocalDate> {

  private final LocalDate date;

  public FindWeekDayRelativeToDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public LocalDate apply(FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed) {
    LocalDate result = moveDateToFirstOccurrenceOfWeekday(fixedWeekdayRelativeToFixed, date);
    final int days = determineNumberOfDays(fixedWeekdayRelativeToFixed);
    result = fixedWeekdayRelativeToFixed.when() == Relation.AFTER ? result.plusDays(days) : result.minusDays(days);
    return result;
  }

  /**
   * Moves the day to the first/next occurrence of the weekday and direction specified
   *
   * @param f   the specification of the weekday and direction of movement
   * @param day the day to move
   * @return the day moved to the weekday and in the direction as specified
   */
  private LocalDate moveDateToFirstOccurrenceOfWeekday(FixedWeekdayRelativeToFixed f, LocalDate day) {
    final TemporalAdjuster adjuster;
    switch (f.when()) {
      case AFTER:
        adjuster = next(f.weekday());
        break;
      case BEFORE:
        adjuster = previous(f.weekday());
        break;
      case CLOSEST:
        adjuster = closest(f.weekday());
        break;
      default:
        throw new IllegalArgumentException("Unsupported relative adjustment: " + f.when());
    }
    return day.with(adjuster);
  }

  /**
   * Determines the number of days to move from the XML enumeration.
   *
   * @param f the enumeration value
   * @return the number of days
   */
  private int determineNumberOfDays(FixedWeekdayRelativeToFixed f) {
    if (f.when() == Relation.CLOSEST) {
      return 0;
    }
    switch (f.which()) {
      case SECOND:
        return 7;
      case THIRD:
        return 14;
      case FOURTH:
        return 21;
      default:
        return 0;
    }
  }

  /**
   * Returns the closest day-of-week adjuster, which adjusts the date to the
   * first occurrence of the specified day-of-week closest to the date being
   * adjusted unless it is already on that day in which case the same object
   * is returned.
   * <p>
   * The ISO calendar system behaves as follows:<br>
   * The input 2011-01-15 (a Saturday) for parameter (MONDAY) will return
   * 2011-01-17 (two days later).<br>
   * The input 2011-01-15 (a Saturday) for parameter (WEDNESDAY) will return
   * 2011-01-12 (three days earlier).<br>
   * The input 2011-01-15 (a Saturday) for parameter (SATURDAY) will return
   * 2011-01-15 (same as input).
   * <p>
   * The behavior is suitable for use with most calendar systems. It uses the
   * {@code DAY_OF_WEEK} field and the {@code DAYS} unit, and assumes a seven
   * day week.
   *
   * @param dayOfWeek the day-of-week to check for or move the date to, not null
   * @return the closest day-of-week adjuster, not null
   */
  private static TemporalAdjuster closest(DayOfWeek dayOfWeek) {
    return temporal -> {
      final Temporal previous = temporal.with(previousOrSame(dayOfWeek));
      final Temporal next = temporal.with(nextOrSame(dayOfWeek));
      final int previousDays = Days.between(temporal, previous).abs().getAmount();
      final int nextDays = Days.between(temporal, next).abs().getAmount();
      return (previousDays <= nextDays ? previous : next);
    };
  }
}
