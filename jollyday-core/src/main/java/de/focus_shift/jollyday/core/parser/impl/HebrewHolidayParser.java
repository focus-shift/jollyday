package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.chrono.HebrewChronology;
import de.focus_shift.jollyday.core.parser.HolidayParser;
import de.focus_shift.jollyday.core.parser.functions.CalculateRelativeDatesFromChronologyWithinGregorianYear;
import de.focus_shift.jollyday.core.parser.functions.CreateHoliday;
import de.focus_shift.jollyday.core.parser.predicates.ValidLimitation;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

/**
 * This parser calculates gregorian dates for the different hebrew (Jewish lunisolar
 * calendar) holidays.
 */
public class HebrewHolidayParser implements HolidayParser {

  public static final HebrewChronology HEBREW = HebrewChronology.INSTANCE;

  /**
   * Year (Gregorian) from which the "5 Iyar on Monday moves to Tuesday" postponement rule
   * for Yom Ha'atzmaut applies. Before this year, a Monday raw date was observed as-is.
   */
  private static final int YOM_HAATZMAUT_MONDAY_RULE_START_YEAR = 2004;

  @Override
  public @NonNull List<Holiday> parse(@NonNull final Year year, @NonNull final HolidayConfigurations holidays) {
    return holidays.hebrewHolidays().stream()
      .filter(new ValidLimitation(year))
      .flatMap(hebrewHoliday -> {

        final Stream<LocalDate> hebrewHolidays = switch (hebrewHoliday.type()) {
          case ROSH_HASHANA ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 1, HEBREW, 0).apply(year);
          case ROSH_HASHANA_II ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 2, HEBREW, 0).apply(year);
          case YOM_KIPPUR ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 10, HEBREW, 0).apply(year);
          case SUKKOT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 15, HEBREW, 0).apply(year);
          case SHEMINI_ATZERET ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(7, 22, HEBREW, 0).apply(year);
          case PESACH ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 15, HEBREW, 0).apply(year);
          case PESACH_VII ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(1, 21, HEBREW, 0).apply(year);
          case SHAVUOT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(3, 6, HEBREW, 0).apply(year);
          // Yom Ha'atzmaut is nominally 5 Iyar, but is postponed to avoid falling adjacent
          // to Shabbat. 5 Iyar can only ever fall on Monday, Wednesday, Friday or Saturday:
          //  - Friday -> observed on the preceding Thursday
          //  - Saturday -> observed on the preceding Thursday
          //  - Monday -> observed as-is until 2004, then postponed to Tuesday from 2004 on
          //    (so that Yom HaZikaron, the day before, never falls on Shabbat)
          //  - Wednesday -> observed as-is
          // (empirically verified against 1949-2046 published observance dates)
          case YOM_HAATZMAUT ->
            new CalculateRelativeDatesFromChronologyWithinGregorianYear(2, 5, HEBREW, 0).apply(year)
              .map(HebrewHolidayParser::postponeYomHaatzmaut);
        };

        return hebrewHolidays
          .map(date -> new CreateHoliday(date).apply(hebrewHoliday));

      })
      .toList();
  }

  private static LocalDate postponeYomHaatzmaut(final LocalDate rawDate) {
    final DayOfWeek dayOfWeek = rawDate.getDayOfWeek();
    if (dayOfWeek == DayOfWeek.FRIDAY) {
      return rawDate.minusDays(1);
    }
    if (dayOfWeek == DayOfWeek.SATURDAY) {
      return rawDate.minusDays(2);
    }
    if (dayOfWeek == DayOfWeek.MONDAY && rawDate.getYear() >= YOM_HAATZMAUT_MONDAY_RULE_START_YEAR) {
      return rawDate.plusDays(1);
    }
    return rawDate;
  }
}
