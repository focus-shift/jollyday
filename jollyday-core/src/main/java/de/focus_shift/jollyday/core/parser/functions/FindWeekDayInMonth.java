package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

import static de.focus_shift.jollyday.core.spi.Occurrance.LAST;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

public class FindWeekDayInMonth implements Function<FixedWeekdayInMonth, LocalDate> {

  private final Year year;

  public FindWeekDayInMonth(final Year year) {
    this.year = year;
  }

  @Override
  public LocalDate apply(final FixedWeekdayInMonth fixedWeekdayInMonth) {
    final LocalDate date = LocalDate.of(year.getValue(), fixedWeekdayInMonth.month(), 1);
    final DayOfWeek weekday = fixedWeekdayInMonth.weekday();

    if (LAST == fixedWeekdayInMonth.which()) {
      return date.with(lastInMonth(weekday));
    }

    return date.with(dayOfWeekInMonth(fixedWeekdayInMonth.which().ordinal() + 1, weekday));
  }
}
