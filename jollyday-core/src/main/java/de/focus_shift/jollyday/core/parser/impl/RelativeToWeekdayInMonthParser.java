package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayInMonth;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * RelativeToWeekdayInMonthParser class.
 * </p>
 */
public class RelativeToWeekdayInMonthParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.relativeToWeekdayInMonth().stream()
      .filter(new ValidLimitation(year))
      .map(relativeToWeekdayInMonth -> resolveHolidayForRelativeToWeekdayInMonth(year, relativeToWeekdayInMonth))
      .collect(toList());
  }

  private Holiday resolveHolidayForRelativeToWeekdayInMonth(final Year year, final RelativeToWeekdayInMonth rwm) {
    final LocalDate baseDate = new FindWeekDayInMonth(year).apply(rwm.weekdayInMonth());

    final int currentDayValue = baseDate.getDayOfWeek().getValue();
    final int targetDayValue = rwm.weekday().getValue();
    final int direction = (rwm.when() == BEFORE ? -1 : 1);

    int daysDifference = targetDayValue - currentDayValue;
    if (direction < 0 && daysDifference >= 0) {
      daysDifference -= 7;
    } else if (direction > 0 && daysDifference <= 0) {
      daysDifference += 7;
    }

    final LocalDate resultDate = baseDate.plusDays(daysDifference);
    return new CreateHoliday(resultDate).apply(rwm);
  }
}
