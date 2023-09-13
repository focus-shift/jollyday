package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateEasterSunday;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This parser creates holidays relative to easter sunday.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class RelativeToEasterSundayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(int year, Holidays holidays) {
    return holidays.relativeToEasterSunday().stream()
      .filter(new ValidLimitation(year))
      .map(res -> new DescribedDateHolder(res, new CalculateEasterSunday(year).apply(res.chronology()).plus(res.days())))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
