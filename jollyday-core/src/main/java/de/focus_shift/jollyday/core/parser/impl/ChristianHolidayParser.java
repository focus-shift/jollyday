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

        final LocalDate actualDate;
        switch (christianHolidayConfiguration.type()) {
          case EASTER:
            actualDate = easterSunday;
            break;
          case CLEAN_MONDAY:
          case SHROVE_MONDAY:
            actualDate = easterSunday.minusDays(48);
            break;
          case MARDI_GRAS:
          case CARNIVAL:
            actualDate = easterSunday.minusDays(47);
            break;
          case ASH_WEDNESDAY:
            actualDate = easterSunday.minusDays(46);
            break;
          case MAUNDY_THURSDAY:
            actualDate = easterSunday.minusDays(3);
            break;
          case GOOD_FRIDAY:
            actualDate = easterSunday.minusDays(2);
            break;
          case EASTER_SATURDAY:
            actualDate = easterSunday.minusDays(1);
            break;
          case EASTER_MONDAY:
            actualDate = easterSunday.plusDays(1);
            break;
          case EASTER_TUESDAY:
            actualDate = easterSunday.plusDays(2);
            break;
          case GENERAL_PRAYER_DAY:
            actualDate = easterSunday.plusDays(26);
            break;
          case ASCENSION_DAY:
            actualDate = easterSunday.plusDays(39);
            break;
          case PENTECOST:
          case WHIT_SUNDAY:
            actualDate = easterSunday.plusDays(49);
            break;
          case WHIT_MONDAY:
          case PENTECOST_MONDAY:
            actualDate = easterSunday.plusDays(50);
            break;
          case CORPUS_CHRISTI:
            actualDate = easterSunday.plusDays(60);
            break;
          case SACRED_HEART:
            actualDate = easterSunday.plusDays(68);
            break;
          default:
            throw new IllegalArgumentException("Unknown christian holiday type " + christianHolidayConfiguration.type());
        }

        return new MoveDateRelative(actualDate).apply(christianHolidayConfiguration)
          .map(observedDate -> new CreateHoliday(actualDate, observedDate))
          .orElseGet(() -> new CreateHoliday(actualDate))
          .apply(christianHolidayConfiguration);

      })
      .collect(toList());
  }
}
