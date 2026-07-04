package de.focus_shift.jollyday.core.parser.functions;

import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Function;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Converts a legacy {@link Calendar} into a {@link LocalDate}.
 *
 * @deprecated since 2.13.0, for removal. This helper only exists to bridge the legacy
 * {@link java.util.Calendar} API, which has been superseded by the modern, immutable and
 * thread-safe {@code java.time} (JSR-310) API. Work with {@link LocalDate} directly instead.
 */
@Deprecated(since = "2.13.0", forRemoval = true)
public class CalendarToLocalDate implements Function<Calendar, LocalDate> {

  @Override
  public @NonNull LocalDate apply(@NonNull final Calendar calendar) {
    return LocalDate.of(calendar.get(YEAR), calendar.get(MONTH) + 1, calendar.get(DAY_OF_MONTH));
  }
}
