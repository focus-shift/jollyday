package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.HolidayParser;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.functions.FindWeekDayBetween;
import de.focus_shift.parser.functions.FixedToLocalDate;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.Holidays;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Parses the configuration for fixed weekdays between two fixed dates.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class FixedWeekdayBetweenFixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(int year, Holidays holidays) {
    return holidays.fixedWeekdayBetweenFixed().stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> new DescribedDateHolder(fwm,
          new FindWeekDayBetween(
            new FixedToLocalDate(year).apply(fwm.from()),
            new FixedToLocalDate(year).apply(fwm.to())
          ).apply(fwm)
        )
      )
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
