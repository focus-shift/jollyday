package de.jollyday.parser.impl;

import de.jollyday.Holiday;
import de.jollyday.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.jollyday.parser.functions.CreateHoliday;
import de.jollyday.parser.predicates.ValidLimitation;
import de.jollyday.spi.EthiopianOrthodoxHoliday;
import org.threeten.extra.chrono.CopticChronology;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Calculates the ethiopian orthodox holidays.
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class EthiopianOrthodoxHolidayParser implements Function<Integer, List<Holiday>> {

  private final List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidayStream;

  public EthiopianOrthodoxHolidayParser(List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidayStream) {
    this.ethiopianOrthodoxHolidayStream = ethiopianOrthodoxHolidayStream;
  }

  @Override
  public List<Holiday> apply(Integer year) {
    return ethiopianOrthodoxHolidayStream.stream()
      .filter(new ValidLimitation(year))
      .flatMap(eoh -> {
        final Stream<LocalDate> ethiopianHolidays;
        switch (eoh.type()) {
          case TIMKAT:
            ethiopianHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(5, 10, CopticChronology.INSTANCE, 0).apply(year);
            break;
          case ENKUTATASH:
            ethiopianHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, CopticChronology.INSTANCE, 0).apply(year);
            break;
          case MESKEL:
            ethiopianHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 17, CopticChronology.INSTANCE, 0).apply(year);
            break;
          default:
            throw new IllegalArgumentException("Unknown ethiopian orthodox holiday type " + eoh.type());
        }
        return ethiopianHolidays.map(date -> new CreateHoliday(date).apply(eoh));
      })
      .collect(toList());
  }
}
