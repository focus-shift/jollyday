package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static java.util.stream.Collectors.toList;

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
  public List<Holiday> parse(final Year year, final HolidayConfigurations holidays) {
    return holidays.relativeToFixed().stream()
      .filter(new ValidLimitation(year))
      .map(relativeToFixed -> moveToRelativeDate(year, relativeToFixed))
      .collect(toList());
  }

  private Holiday moveToRelativeDate(final Year year, final RelativeToFixedHolidayConfiguration rf) {
    LocalDate fixed = new FixedToLocalDate(year).apply(rf.date());
    if (rf.weekday() != null) {
      fixed = moveToWeekday(fixed, rf.weekday(), rf.when());
    } else if (rf.days() != null) {
      fixed = moveByDays(fixed, rf.days(), rf.when());
    }
    return new CreateHoliday(fixed).apply(rf);
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

  private LocalDate moveByDays(LocalDate date, Days days, Relation relation) {
    return relation == BEFORE ? date.minus(days) : date.plus(days);
  }
}
