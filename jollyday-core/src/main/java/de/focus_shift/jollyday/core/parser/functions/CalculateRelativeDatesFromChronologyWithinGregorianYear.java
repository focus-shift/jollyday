package de.focus_shift.jollyday.core.parser.functions;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;

/**
 * Returns a stream of gregorian dates based in the given month and day in the given chronology.
 * <p>
 * Examples:
 * The islamic year is about 11 days shorter
 * than the gregorian there may be more than one occurrence of an islamic
 * date in a gregorian year. i.e.: In the gregorian year 2008 there were
 * two 1/1. They occurred on 1/10 and 12/29.
 */
public class CalculateRelativeDatesFromChronologyWithinGregorianYear implements IntFunction<Stream<LocalDate>> {

  private final int targetMonth;
  private final int targetDay;
  private final Chronology targetChronology;
  private final int relativeShift;

  public CalculateRelativeDatesFromChronologyWithinGregorianYear(final int targetMonth, final int targetDay, final Chronology targetChronology, final int relativeShift) {
    this.targetMonth = targetMonth;
    this.targetDay = targetDay;
    this.targetChronology = targetChronology;
    this.relativeShift = relativeShift;
  }

  @Override
  public Stream<LocalDate> apply(final int gregorianYear) {
    final int absoluteShift = Math.abs(relativeShift);

    final LocalDate firstGregorianDate = LocalDate.of(gregorianYear, JANUARY, 1);
    final LocalDate lastGregorianDate = LocalDate.of(gregorianYear, DECEMBER, 31);

    final ChronoLocalDate firstTargetDate = targetChronology.date(firstGregorianDate.minusDays(absoluteShift));
    final ChronoLocalDate lastTargetDate = targetChronology.date(lastGregorianDate.plusDays(absoluteShift));

    int targetYear = firstTargetDate.get(ChronoField.YEAR);
    final int lastYear = lastTargetDate.get(ChronoField.YEAR);

    final Stream.Builder<LocalDate> builder = Stream.builder();
    while (targetYear <= lastYear) {
      final ChronoLocalDate date = targetChronology.date(targetYear, targetMonth, targetDay).plus(relativeShift, ChronoUnit.DAYS);
      if (!firstGregorianDate.isAfter(date) && !lastGregorianDate.isBefore(date)) {
        builder.accept(LocalDate.from(date));
      }
      targetYear++;
    }
    return builder.build();
  }
}
