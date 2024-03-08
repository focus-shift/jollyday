package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.FixedToLocalDate;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Relation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The Class RelativeToFixedParser.
 */
public class RelativeToFixedParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final int year, final Holidays holidays) {
    return holidays.relativeToFixed().stream()
      .filter(new ValidLimitation(year))
      .map(rf -> {
        LocalDate fixed = new FixedToLocalDate(year).apply(rf.date());
        if (rf.weekday() != null) {
          // if weekday is set -> move to weekday
          DayOfWeek day = rf.weekday();
          int direction = (rf.when() == Relation.BEFORE ? -1 : 1);
          // don't test start day
          fixed = fixed.plusDays(direction);
          while (fixed.getDayOfWeek() != day) {
            fixed = fixed.plusDays(direction);
          }
        } else if (rf.days() != null) {
          // if number of days set -> move number of days
          fixed = fixed.plus(rf.when() == Relation.BEFORE ? rf.days().negated() : rf.days());
        }
        return new CreateHoliday(fixed).apply(rf);
      })
      .collect(toList());
  }
}
