package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayInMonth;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Relation;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

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
      .map(rwm -> {
        LocalDate date = new FindWeekDayInMonth(year).apply(rwm.weekdayInMonth()).plusDays(1);
        int direction = (rwm.when() == Relation.BEFORE ? -1 : 1);
        while (date.getDayOfWeek() != rwm.weekday()) {
          date = date.plusDays(direction);
        }
        return new CreateHoliday(date).apply(rwm);
      })
      .collect(toList());
  }
}
