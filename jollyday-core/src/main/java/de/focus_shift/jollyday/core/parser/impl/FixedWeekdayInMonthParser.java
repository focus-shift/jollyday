package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayInMonth;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedWeekdayInMonthParser.
 */
public class FixedWeekdayInMonthParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.fixedWeekdays().stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> new DescribedDateHolder(fwm, new FindWeekDayInMonth(year).apply(fwm)))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
