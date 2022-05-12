package de.focus_shift.parser.impl;

import de.focus_shift.Holiday;
import de.focus_shift.parser.functions.CalculateEasterSunday;
import de.focus_shift.parser.functions.CreateHoliday;
import de.focus_shift.parser.predicates.ValidLimitation;
import de.focus_shift.spi.RelativeToEasterSunday;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * This parser creates holidays relative to easter sunday.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class RelativeToEasterSundayParser implements Function<Integer, List<Holiday>> {

  private final List<RelativeToEasterSunday> relativeToEasterSundays;

  public RelativeToEasterSundayParser(List<RelativeToEasterSunday> relativeToEasterSundays) {
    this.relativeToEasterSundays = relativeToEasterSundays;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return relativeToEasterSundays.stream()
      .filter(new ValidLimitation(year))
      .map(res -> new DescribedDateHolder(res, new CalculateEasterSunday(year).apply(res.chronology()).plus(res.days())))
      .map(holder -> new CreateHoliday(holder.getDate()).apply(holder.getDescribed()))
      .collect(toList());
  }
}
