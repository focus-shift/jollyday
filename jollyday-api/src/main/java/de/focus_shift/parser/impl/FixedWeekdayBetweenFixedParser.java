package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.functions.FindWeekDayBetween;
import de.focus_shift.parser.functions.FixedToLocalDate;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.FixedWeekdayBetweenFixed;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Parses the configuration for fixed weekdays between two fixed dates.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class FixedWeekdayBetweenFixedParser implements Function<Integer, List<Holiday>> {

  private final List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed;

  public FixedWeekdayBetweenFixedParser(List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed) {
    this.fixedWeekdayBetweenFixed = fixedWeekdayBetweenFixed;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return fixedWeekdayBetweenFixed.stream()
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
