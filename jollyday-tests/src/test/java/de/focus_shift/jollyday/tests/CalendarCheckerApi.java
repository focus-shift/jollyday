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
   *     .notValidBetween(Year.of(1900), Year.of(1953))
   *     .validBetween(Year.of(1954), Year.of(1990))
   *     .notValidBetween(Year.of(1991), Year.of(2500))
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
    /**
     * Checks for a fixed holiday with the given property key, month, and day.
     *
     * @param propertyKey the property key of the holiday
     * @param month       the month of the holiday
     * @param day         the day of the holiday
     * @return properties for further assertions
     */
    Properties hasFixedHoliday(final String propertyKey, final Month month, final int day);

    /**
     * Checks for a fixed holiday with the given property key, month, day, and holiday type.
     *
     * @param propertyKey the property key of the holiday
     * @param month       the month of the holiday
     * @param day         the day of the holiday
     * @param type        the type of the holiday
     * @return properties for further assertions
     */
    Properties hasFixedHoliday(final String propertyKey, final Month month, final int day, final HolidayType type);

    /**
     * Checks for a Christian holiday with the given property key.
     *
     * @param propertyKey the property key of the holiday
     * @return properties for further assertions
     */
    Properties hasChristianHoliday(final String propertyKey);

    /**
     * Checks for a Christian holiday with the given property key and holiday type.
     *
     * @param propertyKey the property key of the holiday
     * @param type        the type of the holiday
     * @return properties for further assertions
     */
    Properties hasChristianHoliday(final String propertyKey, final HolidayType type);

    /**
     * Checks for an Islamic holiday with the given property key.
     *
     * @param propertyKey the property key of the holiday
     * @return properties for further assertions
     */
    Properties hasIslamicHoliday(final String propertyKey);

    /**
     * Checks for an Islamic holiday with the given property key and holiday type.
     *
     * @param propertyKey the property key of the holiday
     * @param type        the type of the holiday
     * @return properties for further assertions
     */
    Properties hasIslamicHoliday(final String propertyKey, final HolidayType type);

    /**
     * Checks for an Ethiopian Orthodox holiday with the given property key.
     *
     * @param propertyKey the property key of the holiday
     * @return properties for further assertions
     */
    Properties hasEthiopianOrthodoxHoliday(final String propertyKey);

    /**
     * Checks for an Ethiopian Orthodox holiday with the given property key and holiday type.
     *
     * @param propertyKey the property key of the holiday
     * @param type        the type of the holiday
     * @return properties for further assertions
     */
    Properties hasEthiopianOrthodoxHoliday(final String propertyKey, final HolidayType type);
  }

  interface Properties extends Subdivision, Between, Shift, Check {
  }

  interface Subdivision extends Check {
    /**
     * Specifies that the holiday is valid in the given subdivisions.
     *
     * @param subdivisions the subdivisions where the holiday is valid
     * @return properties for further assertions
     */
    Properties inSubdivision(final String... subdivisions);
  }

  interface Between extends Check {
    /**
     * Specifies that the holiday is valid until the given year.
     *
     * @param to the year until which the holiday is valid
     * @return properties for further assertions
     */
    Properties validTo(Year to);

    /**
     * Specifies that the holiday is valid from the given year.
     *
     * @param from the year from which the holiday is valid
     * @return properties for further assertions
     */
    Properties validFrom(Year from);

    /**
     * Specifies that the holiday is valid between the given years.
     *
     * @param from the starting year
     * @param to   the ending year
     * @return properties for further assertions
     */
    Properties validBetween(Year from, Year to);

    /**
     * Specifies that the holiday is not valid between the given years.
     *
     * @param from the starting year
     * @param to   the ending year
     * @return properties for further assertions
     */
    Properties notValidBetween(Year from, Year to);
  }

  interface Shift extends Check {
    /**
     * Specifies that the holiday can be moved from one day of the week to another.
     *
     * @param from the original day of the week
     * @param to   the target day of the week
     * @return properties for further assertions
     */
    Properties canBeMovedFrom(DayOfWeek from, DayOfWeek to);

    /**
     * Specifies that the holiday can be moved from one day of the week to another using an adjuster.
     *
     * @param from     the original day of the week
     * @param adjuster the adjuster to use for moving
     * @param to       the target day of the week
     * @return properties for further assertions
     */
    Properties canBeMovedFrom(DayOfWeek from, Adjuster adjuster, DayOfWeek to);
  }

  interface Check {
    /**
     * Chains another holiday assertion.
     *
     * @return the holiday assertion object
     */
    Holiday and();

    /**
     * Performs the holiday checks and assertions.
     */
    void check();
  }
}
