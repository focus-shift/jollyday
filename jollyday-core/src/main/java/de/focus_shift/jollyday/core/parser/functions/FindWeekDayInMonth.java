package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Occurrance;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

public class FindWeekDayInMonth implements Function<FixedWeekdayInMonth, LocalDate> {

  private final int year;

  public FindWeekDayInMonth(int year) {
    this.year = year;
  }

  @Override
  public LocalDate apply(FixedWeekdayInMonth fixedWeekdayInMonth) {
    final LocalDate date = LocalDate.of(year, fixedWeekdayInMonth.month(), 1);
    final DayOfWeek weekday = fixedWeekdayInMonth.weekday();
    if (Occurrance.LAST == fixedWeekdayInMonth.which()) {
      return date.with(lastInMonth(weekday));
    }
    return date.with(dayOfWeekInMonth(fixedWeekdayInMonth.which().ordinal() + 1, weekday));
  }
}
