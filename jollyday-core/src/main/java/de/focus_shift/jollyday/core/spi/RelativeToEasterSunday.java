package de.focus_shift.jollyday.core.spi;

import org.threeten.extra.Days;

import java.time.chrono.Chronology;

/**
 * Represents the configuration of a holiday that is in relation to Easter Sunday and can be
 *
 * <ul>
 *   <li>Limited</li>
 *   <li>Described</li>
 * </ul>
 * <p>
 * Example: A holiday 42 days after Easter Sunday.
 * <p>
 * The difference to {@link ChristianHoliday} is that these holidays are based on Easter Sunday but no christian holidays.
 * <p>
 * The {@link de.focus_shift.jollyday.core.parser.impl.RelativeToEasterSundayParser} is used.
 */
public interface RelativeToEasterSunday extends Described, Limited {

  /**
   * Describes the {@link Chronology} of the holiday
   *
   * @return the chronology of the holiday
   */
  Chronology chronology();

  /**
   * Describes the number of days that will be added to Easter Sunday.
   *
   * @return the additional number of days
   */
  Days days();

}
