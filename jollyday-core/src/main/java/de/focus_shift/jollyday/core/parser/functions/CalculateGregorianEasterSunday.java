package de.focus_shift.jollyday.core.parser.functions;

import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

import static java.time.Month.APRIL;
import static java.time.Month.MARCH;

public class CalculateGregorianEasterSunday implements Function<Year, LocalDate> {

  @Override
  public @NonNull LocalDate apply(@NonNull final Year year) {
    final int yearValue = year.getValue();
    final int goldenNumber = yearValue % 19;
    final int century = yearValue / 100;
    final int yearInCentury = yearValue % 100;
    final int centuryDiv4 = century / 4;
    final int centuryMod4 = century % 4;
    final int centuryCorrection = (century + 8) / 25;
    final int centuryCorrectionDiv3 = (century - centuryCorrection + 1) / 3;
    final int epact = (19 * goldenNumber + century - centuryDiv4 - centuryCorrectionDiv3 + 15) % 30;
    final int yearInCenturyDiv4 = yearInCentury / 4;
    final int yearInCenturyMod4 = yearInCentury % 4;
    final int weekdayOffset = (32 + 2 * centuryMod4 + 2 * yearInCenturyDiv4 - epact - yearInCenturyMod4) % 7;
    final int monthCorrection = (goldenNumber + 11 * epact + 22 * weekdayOffset) / 451;
    final int daysAfterFebruary = epact + weekdayOffset - 7 * monthCorrection + 114;
    final int month = daysAfterFebruary / 31;
    final int day = (daysAfterFebruary % 31) + 1;
    return LocalDate.of(year.getValue(), (month == 3 ? MARCH : APRIL), day);
  }
}
