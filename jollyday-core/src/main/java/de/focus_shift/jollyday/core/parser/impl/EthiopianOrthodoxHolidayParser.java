package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;
import org.threeten.extra.chrono.CopticChronology;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Calculates the ethiopian orthodox holidays.
 */
public class EthiopianOrthodoxHolidayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.ethiopianOrthodoxHolidays().stream()
      .filter(new ValidLimitation(year))
      .flatMap(eoh -> {
        final Stream<LocalDate> ethiopianHolidays = switch (eoh.type()) {
          case TIMKAT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(5, 10, CopticChronology.INSTANCE, 0).apply(year);
          case ENKUTATASH ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, CopticChronology.INSTANCE, 0).apply(year);
          case MESKEL ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 17, CopticChronology.INSTANCE, 0).apply(year);
        };
        return ethiopianHolidays.map(date -> new CreateHoliday(date).apply(eoh));
      })
      .collect(toList());
  }
}
