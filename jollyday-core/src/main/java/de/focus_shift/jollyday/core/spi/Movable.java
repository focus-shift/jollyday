package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;
import java.util.List;
import org.jspecify.annotations.NonNull;

/**
 * Provides the functionality to move a holiday based on the {@link MovingCondition}s to another day
 * of the week.
 */
public interface Movable {

  /**
   * Describes the different moving conditions that have been configured for a specific holiday.
   *
   * @return the configured {@link MovingCondition} for this holiday
   */
  @NonNull List<MovingCondition> conditions();

  /**
   * Describes how a holiday, that implements the {@link Movable} interface, can be moved from a day
   * of a week to another day of a week.
   *
   * <p>Examples:
   *
   * <ul>
   *   <li>A holiday that falls on a Sunday (weekday) can be moved to the next (with) Monday
   *       (substitute).
   *   <li>A holiday that falls on a Saturday (weekday) can be moved to the previous (with) Friday
   *       (substitute).
   * </ul>
   */
  interface MovingCondition {

    enum With {

      /* Moves the holiday to the next weekday */
      NEXT,

      /* Moves the holiday to the previous weekday */
      PREVIOUS
    }

    /**
     * Represents the day of the week as trigger to move the holiday if the holiday would occur oh
     * this weekday.
     *
     * @return the weekday on which the holiday will be moved to another weekday
     */
    @NonNull DayOfWeek weekday();

    /**
     * Represents if the holiday will be moved to the previous or next weekday.
     *
     * @return the rule in which direction (next or previous) the holiday will be moved.
     */
    @NonNull With with();

    /**
     * Represents the substitution weekday for the holiday
     *
     * @return the weekday the holiday will be moved to
     */
    @NonNull DayOfWeek substitute();
  }
}
