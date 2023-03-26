package de.focus_shift.parser.functions;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Function;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CalendarToLocalDate implements Function<Calendar, LocalDate> {

  @Override
  public LocalDate apply(Calendar calendar) {
    return LocalDate.of(calendar.get(YEAR), calendar.get(MONTH) + 1, calendar.get(DAY_OF_MONTH));
  }
}
