package de.focus_shift.jollyday.core.parser.functions;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Function;
import org.jspecify.annotations.NonNull;

public class CalendarToLocalDate implements Function<Calendar, LocalDate> {

  @Override
  public @NonNull LocalDate apply(@NonNull final Calendar calendar) {
    return LocalDate.of(calendar.get(YEAR), calendar.get(MONTH) + 1, calendar.get(DAY_OF_MONTH));
  }
}
