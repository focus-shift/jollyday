package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.functions.FindWeekDayInMonth;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.FixedWeekdayInMonth;

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
