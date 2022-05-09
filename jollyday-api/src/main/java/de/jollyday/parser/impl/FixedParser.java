package de.jollyday.parser.impl;

import de.jollyday.Holiday;
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.functions.FixedToLocalDate;
import de.jollyday.parser.functions.MoveDateRelative;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.Fixed;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * The Class FixedParser. Parses a fixed date to create a Holiday.
 *
 * @author tboven
 * @version $Id: $
 */
public class FixedParser implements Function<Integer, List<Holiday>> {

  private final List<Fixed> fixed;

  public FixedParser(List<Fixed> fixed) {
    this.fixed = fixed;
  }

  @Override
  public List<Holiday> apply(final Integer year) {
    return fixed.stream()
      .filter(new ValidLimitation(year))
      .map(fixed -> new DescribedDateHolder(fixed, new MoveDateRelative(new FixedToLocalDate(year).apply(fixed)).apply(fixed)))
      .map(describedDateHolder -> new CreateHoliday(describedDateHolder.getDate()).apply(describedDateHolder.getDescribed()))
      .collect(toList());
  }
}
