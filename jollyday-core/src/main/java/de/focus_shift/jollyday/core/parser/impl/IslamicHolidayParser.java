package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.HijrahChronology;
import java.util.List;
import java.util.stream.Stream;

/**
 * This parser calculates gregorian dates for the different islamic holidays.
 */
public class IslamicHolidayParser implements HolidayParser {

  @Override
  public List<Holiday> parse(final Year year, final HolidayConfigurations holidays) {
    return holidays.islamicHolidays().stream()
      .filter(new ValidLimitation(year))
      .flatMap(islamicHoliday -> {

        final Stream<LocalDate> islamicHolidays = switch (islamicHoliday.type()) {
          case NEWYEAR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, HijrahChronology.INSTANCE, 0).apply(year);
          case ASCHURA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HijrahChronology.INSTANCE, 0).apply(year);
          case RAMADAN_END ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, -1).apply(year);
          case ID_AL_FITR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HijrahChronology.INSTANCE, 0).apply(year);
          case ID_AL_FITR_2 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 2, HijrahChronology.INSTANCE, 0).apply(year);
          case ID_AL_FITR_3 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 3, HijrahChronology.INSTANCE, 0).apply(year);
          case ARAFAAT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 9, HijrahChronology.INSTANCE, 0).apply(year);
          case ID_UL_ADHA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 10, HijrahChronology.INSTANCE, 0).apply(year);
          case ID_UL_ADHA_2 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 11, HijrahChronology.INSTANCE, 0).apply(year);
          case ID_UL_ADHA_3 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 12, HijrahChronology.INSTANCE, 0).apply(year);
          case LAILAT_AL_BARAT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(8, 15, HijrahChronology.INSTANCE, 0).apply(year);
          case LAILAT_AL_MIRAJ ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 27, HijrahChronology.INSTANCE, 0).apply(year);
          case LAILAT_AL_QADR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 27, HijrahChronology.INSTANCE, 0).apply(year);
          case MAWLID_AN_NABI ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(3, 12, HijrahChronology.INSTANCE, 0).apply(year);
          case RAMADAN ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 1, HijrahChronology.INSTANCE, 0).apply(year);
        };

        return islamicHolidays
          .map(date -> new DescribedDateHolder(islamicHoliday, date, new MoveDateRelative(date).apply(islamicHoliday).orElse(null)))
          .map(describedDateHolder -> new CreateHoliday(describedDateHolder.getActualDate(), describedDateHolder.getObservedDate()).apply(islamicHoliday));

      })
      .toList();
  }
}
