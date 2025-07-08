package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Relation;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static java.util.stream.Collectors.toList;

/**
 * The Class RelativeToFixedParser.
 */
public class RelativeToFixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.relativeToFixed().stream()
      .filter(new ValidLimitation(year))
      .map(rf -> {
        LocalDate fixed = new FixedToLocalDate(year).apply(rf.date());
        if (rf.weekday() != null) {
          fixed = moveToWeekday(fixed, rf.weekday(), rf.when());
        } else if (rf.days() != null) {
          fixed = moveByDays(fixed, rf.days(), rf.when());
        }
        return new CreateHoliday(fixed).apply(rf);
      })
      .collect(toList());
  }

  private LocalDate moveToWeekday(LocalDate date, DayOfWeek targetDay, Relation relation) {

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

  /**
   * Moves the given date by the specified number of days in the given direction.
   */
  private LocalDate moveByDays(LocalDate date, Days days, Relation relation) {
    return relation == BEFORE ? date.minus(days) : date.plus(days);
  }
}
