package de.jollyday.parser.impl;

import de.jollyday.Holiday;
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.functions.FindWeekDayInMonth;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.FixedWeekdayInMonth;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedWeekdayInMonthParser.
 *
 * @author tboven
 * @version $Id: $
 */
public class FixedWeekdayInMonthParser implements Function<Integer, List<Holiday>> {

  private final List<FixedWeekdayInMonth> fixedWeekdayInMonths;

  public FixedWeekdayInMonthParser(List<FixedWeekdayInMonth> fixedWeekdayInMonths) {
    this.fixedWeekdayInMonths = fixedWeekdayInMonths;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return fixedWeekdayInMonths.stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> new DescribedDateHolder(fwm, new FindWeekDayInMonth(year).apply(fwm)))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
