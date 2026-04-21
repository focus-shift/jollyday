package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FindWeekDayInMonth;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

/**
 * The Class FixedWeekdayInMonthParser.
 */
public class FixedWeekdayInMonthParser implements HolidayParser {

  @Override
  public @NonNull List<Holiday> parse(@NonNull final Year year, @NonNull final HolidayConfigurations holidays) {
    return holidays.fixedWeekdays().stream()
      .filter(new ValidLimitation(year))
      .map(fwm -> {
        final LocalDate actualDate = new FindWeekDayInMonth(year).apply(fwm);
        final LocalDate observedDate = new MoveDateRelative(actualDate).apply(fwm).orElse(null);
        return new DescribedDateHolder(fwm, actualDate, observedDate);
      })
      .map(holder -> new CreateHoliday(holder.getActualDate(), holder.getObservedDate()).apply(holder.getDescribed()))
      .toList();
  }
}
