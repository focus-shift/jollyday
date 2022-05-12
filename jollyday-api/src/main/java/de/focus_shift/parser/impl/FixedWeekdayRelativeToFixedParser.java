package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.functions.FindWeekDayRelativeToDate;
import de.focus_shift.parser.functions.FixedToLocalDate;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.FixedWeekdayRelativeToFixed;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Parses fixed weekday relative to fixed date.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class FixedWeekdayRelativeToFixedParser implements Function<Integer, List<Holiday>> {

  private final List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed;

  public FixedWeekdayRelativeToFixedParser(List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed) {
    this.fixedWeekdayRelativeToFixed = fixedWeekdayRelativeToFixed;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return fixedWeekdayRelativeToFixed.stream()
      .filter(new ValidLimitation(year))
      .map(fwrf -> new DescribedDateHolder(fwrf, new FindWeekDayRelativeToDate(new FixedToLocalDate(year).apply(fwrf.day())).apply(fwrf)))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}

