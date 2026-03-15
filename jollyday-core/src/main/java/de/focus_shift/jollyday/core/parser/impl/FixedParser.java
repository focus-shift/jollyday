package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import org.jspecify.annotations.NonNull;

/** The Class FixedParser. Parses a fixed date to create a Holiday. */
public class FixedParser implements HolidayParser {

  @Override
  public @NonNull List<Holiday> parse(
      @NonNull final Year year, @NonNull final HolidayConfigurations holidays) {
    return holidays.fixed().stream()
        .filter(new ValidLimitation(year))
        .map(
            fixedConfiguration -> {
              final LocalDate actualDate = new FixedToLocalDate(year).apply(fixedConfiguration);
              final LocalDate observedDate =
                  new MoveDateRelative(actualDate).apply(fixedConfiguration).orElse(null);
              return new DescribedDateHolder(fixedConfiguration, actualDate, observedDate);
            })
        .map(
            describedDateHolder ->
                new CreateHoliday(
                        describedDateHolder.getActualDate(), describedDateHolder.getObservedDate())
                    .apply(describedDateHolder.getDescribed()))
        .toList();
  }
}
