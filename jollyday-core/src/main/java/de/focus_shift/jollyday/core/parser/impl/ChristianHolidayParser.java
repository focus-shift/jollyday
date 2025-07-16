package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateEasterSunday;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This parser creates christian holidays for the given year relative to easter
 * sunday.
 */
public class ChristianHolidayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final Holidays holidays) {
    return holidays.christianHolidays().stream()
      .filter(new ValidLimitation(year))
      .map(christianHolidayConfiguration -> {

        final LocalDate easterSunday = new CalculateEasterSunday(year).apply(christianHolidayConfiguration.chronology());

        final LocalDate actualDate = switch (christianHolidayConfiguration.type()) {
          case EASTER -> easterSunday;
          case CLEAN_MONDAY, SHROVE_MONDAY -> easterSunday.minusDays(48);
          case MARDI_GRAS, CARNIVAL -> easterSunday.minusDays(47);
          case ASH_WEDNESDAY -> easterSunday.minusDays(46);
          case MAUNDY_THURSDAY -> easterSunday.minusDays(3);
          case GOOD_FRIDAY -> easterSunday.minusDays(2);
          case EASTER_SATURDAY -> easterSunday.minusDays(1);
          case EASTER_MONDAY -> easterSunday.plusDays(1);
          case EASTER_TUESDAY -> easterSunday.plusDays(2);
          case GENERAL_PRAYER_DAY -> easterSunday.plusDays(26);
          case ASCENSION_DAY -> easterSunday.plusDays(39);
          case PENTECOST, WHIT_SUNDAY -> easterSunday.plusDays(49);
          case WHIT_MONDAY, PENTECOST_MONDAY -> easterSunday.plusDays(50);
          case CORPUS_CHRISTI -> easterSunday.plusDays(60);
          case SACRED_HEART -> easterSunday.plusDays(68);
        };

        return new MoveDateRelative(actualDate).apply(christianHolidayConfiguration)
          .map(observedDate -> new CreateHoliday(actualDate, observedDate))
          .orElseGet(() -> new CreateHoliday(actualDate))
          .apply(christianHolidayConfiguration);

      })
      .collect(toList());
  }
}
