package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.HolidayParser;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.functions.FixedToLocalDate;
import de.focus_shift.parser.functions.MoveDateRelative;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.Holidays;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedParser. Parses a fixed date to create a Holiday.
 *
 * @author tboven
 * @version $Id: $
 */
public class FixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(Integer year, Holidays holidays) {
    return holidays.fixed().stream()
      .filter(new ValidLimitation(year))
      .map(fixed -> new DescribedDateHolder(fixed, new MoveDateRelative(new FixedToLocalDate(year).apply(fixed)).apply(fixed)))
      .map(describedDateHolder -> new CreateHoliday(describedDateHolder.getDate()).apply(describedDateHolder.getDescribed()))
      .collect(toList());
  }
}
