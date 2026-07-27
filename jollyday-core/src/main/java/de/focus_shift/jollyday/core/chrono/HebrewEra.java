package de.focus_shift.jollyday.core.chrono;

import java.time.DateTimeException;
import java.time.chrono.Era;
import org.jspecify.annotations.NonNull;

/**
 * An era in the Hebrew calendar system.
 * <p>
 * The Hebrew calendar has a single era, counting years since the traditional
 * creation date (Anno Mundi).
 */
public enum HebrewEra implements Era {

  /**
   * The singular era of the Hebrew calendar, 'Anno Mundi'.
   */
  AM;

  /**
   * Obtains an instance of {@code HebrewEra} from an {@code int} value.
   *
   * @param era the era value, only {@code 1} (AM) is valid
   * @return the era, not null
   * @throws DateTimeException if the value is invalid
   */
  public static @NonNull HebrewEra of(final int era) {
    if (era != 1) {
      throw new DateTimeException("Invalid era: " + era);
    }
    return AM;
  }

  @Override
  public int getValue() {
    return 1;
  }
}
