package de.jollyday.parser.impl;

import de.jollyday.Holiday;
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.functions.FindWeekDayBetween;
import de.jollyday.parser.functions.FixedToLocalDate;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.FixedWeekdayBetweenFixed;

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
