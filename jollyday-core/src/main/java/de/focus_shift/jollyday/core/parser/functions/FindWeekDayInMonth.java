package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;

public class FindWeekDayInMonth implements Function<FixedWeekdayInMonthHolidayConfiguration, LocalDate> {

  private final Year year;

  public FindWeekDayInMonth(@ NonNull final Year year) {
    this.year = year;
  }

  @Override
  public @NonNull LocalDate apply(@NonNull final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth) {
    final LocalDate date = LocalDate.of(year.getValue(), fixedWeekdayInMonth.month(), 1);
    final DayOfWeek weekday = fixedWeekdayInMonth.weekday();

    final LocalDate computedDate;
    if (LAST == fixedWeekdayInMonth.which()) {
      computedDate = date.with(lastInMonth(weekday));
    } else {
      computedDate = date.with(dayOfWeekInMonth(fixedWeekdayInMonth.which().ordinal() + 1, weekday));
    }

    if (fixedWeekdayInMonth.avoidHolyWeek()) {
      final LocalDate easterSunday = new CalculateGregorianEasterSunday().apply(year);
      final LocalDate holyWeekStart = easterSunday.minusDays(7);
      if (!computedDate.isBefore(holyWeekStart) && !computedDate.isAfter(easterSunday)) {
        return computedDate.plusWeeks(1);
      }
    }

    return computedDate;
  }
}
