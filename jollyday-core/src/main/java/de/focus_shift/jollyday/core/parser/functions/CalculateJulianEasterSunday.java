package de.focus_shift.jollyday.core.parser.functions;

import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.time.Year;
import java.util.function.Function;

public class CalculateJulianEasterSunday implements Function<Year, LocalDate> {

  @Override
  public LocalDate apply(final Year year) {
    int a, b, c, d, e;
    int x, month, day;
    a = year.getValue() % 4;
    b = year.getValue() % 7;
    c = year.getValue() % 19;
    d = (19 * c + 15) % 30;
    e = (2 * a + 4 * b - d + 34) % 7;
    x = d + e + 114;
    month = x / 31;
    day = (x % 31) + 1;
    return LocalDate.from(JulianChronology.INSTANCE.date(year.getValue(), (month == 3 ? 3 : 4), day));
  }
}
