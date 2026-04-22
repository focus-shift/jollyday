package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.functions.MoveDateRelative;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.HijrahChronology;
import java.util.List;
import java.util.stream.Stream;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

/**
 * This parser calculates gregorian dates for the different islamic holidays.
 */
public class IslamicHolidayParser implements HolidayParser {

  public static final HijrahChronology HIJRAH = HijrahChronology.INSTANCE;

  @Override
  public @NonNull List<Holiday> parse(@NonNull final Year year, @NonNull final HolidayConfigurations holidays) {
    return holidays.islamicHolidays().stream()
      .filter(new ValidLimitation(year))
      .flatMap(islamicHoliday -> {

        final Stream<LocalDate> islamicHolidays = switch (islamicHoliday.type()) {
          case NEWYEAR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 1, HIJRAH, 0).apply(year);
          case ASCHURA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 10, HIJRAH, 0).apply(year);
          case RAMADAN_END ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HIJRAH, -1).apply(year);
          case ID_AL_FITR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HIJRAH, 0).apply(year);
          case ID_AL_FITR_2 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 2, HIJRAH, 0).apply(year);
          case ID_AL_FITR_3 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 3, HIJRAH, 0).apply(year);
          case ARAFAAT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 9, HIJRAH, 0).apply(year);
          case ID_UL_ADHA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 10, HIJRAH, 0).apply(year);
          case ID_UL_ADHA_2 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 11, HIJRAH, 0).apply(year);
          case ID_UL_ADHA_3 ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(12, 12, HIJRAH, 0).apply(year);
          case LAILAT_AL_BARAT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(8, 15, HIJRAH, 0).apply(year);
          case LAILAT_AL_MIRAJ ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 27, HIJRAH, 0).apply(year);
          case LAILAT_AL_QADR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 27, HIJRAH, 0).apply(year);
          case MAWLID_AN_NABI ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(3, 12, HIJRAH, 0).apply(year);
          case RAMADAN ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(9, 1, HIJRAH, 0).apply(year);
          case JUMUATUL_WIDA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(10, 1, HIJRAH, -1).apply(year)
            .map(localDate -> localDate.with(previousOrSame(FRIDAY)));
        };

        return islamicHolidays
          .map(date -> new DescribedDateHolder(islamicHoliday, date, new MoveDateRelative(date).apply(islamicHoliday).orElse(null)))
          .map(describedDateHolder -> new CreateHoliday(describedDateHolder.getActualDate(), describedDateHolder.getObservedDate()).apply(islamicHoliday));

      })
      .toList();
  }
}
