package de.focus_shift.jollyday.core.parser.functions;

import org.jspecify.annotations.NonNull;
import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

public class CalculateJulianEasterSunday implements Function<Year, LocalDate> {

  @Override
  public @NonNull LocalDate apply(@NonNull final Year year) {
    final int leapYearCyclePosition = year.getValue() % 4;
    final int weekDayOffset = year.getValue() % 7;
    final int metonicCyclePosition = year.getValue() % 19;
    final int lunarAgeFactor = (19 * metonicCyclePosition + 15) % 30;
    final int weekDayCorrection = (2 * leapYearCyclePosition + 4 * weekDayOffset - lunarAgeFactor + 34) % 7;
    final int dayOffset = lunarAgeFactor + weekDayCorrection + 114;
    final int month = dayOffset / 31;
    final int day = (dayOffset % 31) + 1;
    return LocalDate.from(JulianChronology.INSTANCE.date(year.getValue(), (month == 3 ? 3 : 4), day));
  }
}
