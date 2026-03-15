package de.focus_shift.jollyday.core.spi;

import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Provides the functionality to limit the years a holiday will occur. By default, a holiday will
 * occur on every year it is requested until this interface will be used and valid from or valid to
 * is set, then the holiday will only occur in this year's. If the cycle is set, a holiday will only
 * occur in the years represented by the cycle.
 *
 * <p>Example: if a holiday is only valid from 1900 until 1910 and a cycle from five years is
 * configured, then the holiday will occur in the years:
 *
 * <ul>
 *   <li>1900
 *   <li>1905
 *   <li>1910
 * </ul>
 */
public interface Limited {

  enum YearCycle {
    EVERY_YEAR,
    ODD_YEARS,
    EVEN_YEARS,
    TWO_YEARS,
    THREE_YEARS,
    FOUR_YEARS,
    FIVE_YEARS,
    SIX_YEARS
  }

  /**
   * Describes the first year a holiday is valid (inclusive)
   *
   * @return the first valid year of a holiday
   */
  @Nullable Year validFrom();

  /**
   * Describes the last year a holiday is valid (inclusive)
   *
   * @return the last valid year of a holiday
   */
  @Nullable Year validTo();

  /**
   * Describes the cycle a holiday is valid in between the years given bei from and to.
   *
   * @return the cycle to limit a holiday between from and to
   */
  @NonNull YearCycle cycle();
}
