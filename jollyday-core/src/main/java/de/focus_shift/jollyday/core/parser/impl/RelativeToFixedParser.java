package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;

/**
 * The Class RelativeToFixedParser.
 * <p>
 * Calculates the holiday date relative to a fixed date, either by moving to a specific weekday
 * or by adding/subtracting a number of days.
 * <p>
 * There are two ways this method adjusts the date:
 * <ul>
 *   <li>If a weekday is given, it moves the fixed date to the next or previous occurrence
 *       of the specified weekday, depending on the relation.</li>
 *   <li>If a number of days but no weekday is given, it adds or subtracts the specified period to/from the fixed date,
 *       depending on the relation.</li>
 * </ul>
 * <p>
 * The resulting date is then used to create the holiday.
 */
public class RelativeToFixedParser implements HolidayParser {

  @Override
  public @NonNull List<Holiday> parse(@NonNull final Year year, @NonNull final HolidayConfigurations holidays) {
    return holidays.relativeToFixed().stream()
      .filter(new ValidLimitation(year))
      .map(relativeToFixed -> moveToRelativeDate(year, relativeToFixed))
      .toList();
  }

  private @NonNull Holiday moveToRelativeDate(@NonNull final Year year, @NonNull final RelativeToFixedHolidayConfiguration rf) {
    LocalDate fixed = new FixedToLocalDate(year).apply(rf.date());

    final Optional<DayOfWeek> weekday = rf.weekday();
    if (weekday.isPresent()) {
      fixed = moveToWeekday(fixed, weekday.get(), rf.when().orElse(null));
    } else {
      final Optional<Days> days = rf.days();
      if (days.isPresent()) {
        fixed = moveByDays(fixed, days.get(), rf.when().orElse(null));
      }
    }

    return new CreateHoliday(fixed).apply(rf);
  }

  private @NonNull LocalDate moveToWeekday(@NonNull final LocalDate date, @NonNull final DayOfWeek targetDay, @Nullable final Relation relation) {

    final int direction = (relation == BEFORE ? -1 : 1);
    final int currentDayValue = date.getDayOfWeek().getValue();
    final int targetDayValue = targetDay.getValue();

    int daysDifference = targetDayValue - currentDayValue;
    if (direction < 0 && daysDifference >= 0) {
      daysDifference -= 7;
    } else if (direction > 0 && daysDifference <= 0) {
      daysDifference += 7;
    }

    return date.plusDays(daysDifference);
  }

  private @NonNull LocalDate moveByDays(@NonNull final LocalDate date, @NonNull final Days days, @Nullable final Relation relation) {
    return relation == BEFORE ? date.minus(days) : date.plus(days);
  }
}
