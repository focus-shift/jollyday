package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedParser. Parses a fixed date to create a Holiday.
 */
public class FixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.fixed().stream()
      .filter(new ValidLimitation(year))
      .map(fixed -> new DescribedDateHolder(fixed, new MoveDateRelative(new FixedToLocalDate(year).apply(fixed)).apply(fixed)))
      .map(describedDateHolder -> new CreateHoliday(describedDateHolder.getDate()).apply(describedDateHolder.getDescribed()))
      .collect(toList());
  }
}
