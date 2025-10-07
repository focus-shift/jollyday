package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.tests.CalendarChecker.Adjuster;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

public interface CalendarCheckerApi {

  /**
   * Creates a new instance of <code>{@link CalendarChecker}</code>.
   * <p>
   * Calling multiple methods on the returned {@link CalendarChecker} is safe as it only interacts with the {@link CalendarCheckerApi}
   * <p>
   * Example:
   * <pre><code class='java'> // you can chain multiple holiday checks
   * assertFor(GERMANY)
   *   .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
   *   .hasFixedHoliday("UNIFICATION", JUNE, 17)
   *     .notBetween(Year.of(1900), Year.of(1953))
   *     .between(Year.of(1954), Year.of(1990))
   *     .notBetween(Year.of(1991), Year.of(2500))
   *   .and()
   *   .hasChristianHoliday("ASCENSION_DAY").and()
   *   .hasChristianHoliday("WHIT_MONDAY")
   *   .check();</code></pre>
   *
   * @param calendar the calendar that should be used for the holiday assertions
   * @return the created holiday assertion object.
   */
  static CalendarChecker assertFor(final HolidayCalendar calendar) {
    return new CalendarChecker(calendar);
  }

  interface Holiday {
    Properties hasFixedHoliday(final String propertyKey, final Month month, final int day);
    Properties hasFixedHoliday(final String propertyKey, final Month month, final int day, final HolidayType type);

    Properties hasChristianHoliday(final String propertyKey);
    Properties hasChristianHoliday(final String propertyKey, final HolidayType type);

    Properties hasIslamicHoliday(final String propertyKey);
    Properties hasIslamicHoliday(final String propertyKey, final HolidayType type);

    Properties hasEthiopianOrthodoxHoliday(final String propertyKey);
    Properties hasEthiopianOrthodoxHoliday(final String propertyKey, final HolidayType type);
  }

  interface Properties extends Subdivision, Between, Shift, Check {
  }

  interface Subdivision extends Check {
    Properties inSubdivision(final String... subdivisions);
  }

  interface Between extends Check  {
    Properties validTo(Year to);
    Properties validFrom(Year from);
    Properties validBetween(Year from, Year to);
    Properties notValidBetween(Year from, Year to);
  }

  interface Shift extends Check  {
    Properties canBeMovedFrom(DayOfWeek from, DayOfWeek to);
    Properties canBeMovedFrom(DayOfWeek from, Adjuster adjuster, DayOfWeek to);
  }

  interface Check {
    Holiday and();
    void check();
  }
}
