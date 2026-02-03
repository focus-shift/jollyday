package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayBetween;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Parses the configuration for fixed weekdays between two fixed dates.
 */
public class FixedWeekdayBetweenFixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final HolidayConfigurations holidays) {
    return holidays.fixedWeekdayBetweenFixed().stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> new DescribedDateHolder(fwm,
          new FindWeekDayBetween(
            new FixedToLocalDate(year).apply(fwm.from()),
            new FixedToLocalDate(year).apply(fwm.to())
          ).apply(fwm)
        )
      )
      .map(holder -> new CreateHoliday(holder.getActualDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
