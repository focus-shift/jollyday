package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayRelativeToDate;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Parses fixed weekday relative to fixed date.
 */
public class FixedWeekdayRelativeToFixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final HolidayConfigurations holidays) {
    return holidays.fixedWeekdayRelativeToFixed().stream()
      .filter(new ValidLimitation(year))
      .map(weekdayRelativeToFixed ->
        new DescribedDateHolder(
          weekdayRelativeToFixed,
          new FindWeekDayRelativeToDate(new FixedToLocalDate(year).apply(weekdayRelativeToFixed.day())).apply(weekdayRelativeToFixed)
        )
      )
      .map(holder -> new CreateHoliday(holder.getActualDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}

