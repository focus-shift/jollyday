package de.focus_shift.jollyday.core.spi;

import java.time.DayOfWeek;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.threeten.extra.Days;

/**
 * Represents the configuration of a holiday that is in relation to a {@link
 * FixedHolidayConfiguration} date.
 *
 * <ul>
 *   <li>If only weekday is set it will use the weekday.
 *   <li>If only days is set is not set it will use days.
 *   <li>If both is set it will use the weekday.
 * </ul>
 *
 * <p>and can be
 *
 * <ul>
 *   <li>Limited
 *   <li>Described
 * </ul>
 *
 * <p>Example: A holiday on tuesday before the second friday in october A holiday on 3 days after
 * the second friday in october
 *
 * <p>The {@link de.focus_shift.jollyday.core.parser.impl.RelativeToFixedParser} is used.
 */
public interface RelativeToFixedHolidayConfiguration extends Described, Limited {

  /**
   * Describes the weekday on which the new holiday occurs
   *
   * @return the weekday of the new holiday
   */
  @Nullable DayOfWeek weekday();

  /**
   * Describes the days in relation to the fixed date of the new holiday
   *
   * @return the days to shift
   */
  @Nullable Days days();

  /**
   * Describes the relation of the new holiday to the fixed date
   *
   * @return the relation
   */
  @Nullable Relation when();

  /**
   * Describes the anchor date for the relation of the new holiday
   *
   * @return anchor date for the new holiday
   */
  @NonNull FixedHolidayConfiguration date();
}
