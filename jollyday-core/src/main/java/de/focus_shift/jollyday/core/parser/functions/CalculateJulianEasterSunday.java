package de.focus_shift.jollyday.core.parser.functions;

import org.threeten.extra.chrono.JulianChronology;

import java.time.LocalDate;
import java.util.function.IntFunction;

public class CalculateJulianEasterSunday implements IntFunction<LocalDate> {

  @Override
  public LocalDate apply(int year) {
    int a, b, c, d, e;
    int x, month, day;
    a = year % 4;
    b = year % 7;
    c = year % 19;
    d = (19 * c + 15) % 30;
    e = (2 * a + 4 * b - d + 34) % 7;
    x = d + e + 114;
    month = x / 31;
    day = (x % 31) + 1;
    return LocalDate.from(JulianChronology.INSTANCE.date(year, (month == 3 ? 3 : 4), day));
  }
}
