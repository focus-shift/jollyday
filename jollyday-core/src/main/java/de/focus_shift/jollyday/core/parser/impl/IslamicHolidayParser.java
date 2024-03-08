package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This parser calculates gregorian dates for the different islamic holidays.
 */
public class IslamicHolidayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final int year, final Holidays holidays) {
    return holidays.islamicHolidays().stream()
      .filter(new ValidLimitation(year))
      .flatMap(islamicHoliday -> {

        final Stream<LocalDate> islamicHolidays;

        switch (islamicHoliday.type()) {
          case NEWYEAR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ASCHURA:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case RAMADAN_END:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, -1).apply(year);
            break;
          case ID_AL_FITR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_AL_FITR_2:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 2, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_AL_FITR_3:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 3, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ARAFAAT:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 9, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 10, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA_2:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 11, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case ID_UL_ADHA_3:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 12, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_BARAT:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(8, 15, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_MIRAJ:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 27, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case LAILAT_AL_QADR:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 27, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case MAWLID_AN_NABI:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(3, 12, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          case RAMADAN:
            islamicHolidays = new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 1, HijrahChronology.INSTANCE, 0).apply(year);
            break;
          default:
            throw new IllegalArgumentException("Unknown islamic holiday " + islamicHoliday.type());
        }

        return islamicHolidays
          .map(date -> new MoveDateRelative(date).apply(islamicHoliday))
          .map(date -> new CreateHoliday(date).apply(islamicHoliday));

      })
      .collect(toList());
  }
}
